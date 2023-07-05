package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateUserDTO;
import com.company.scma.common.dto.EditUserDTO;
import com.company.scma.common.dto.GetUserDTO;
import com.company.scma.common.po.TMember;
import com.company.scma.common.po.TRole;
import com.company.scma.common.po.TUser;
import com.company.scma.common.util.GenerateUtil;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.RoleListVO;
import com.company.scma.common.vo.UserDetailVO;
import com.company.scma.common.vo.UserListVO;
import com.company.scma.service.bizservice.UserBizService;
import com.company.scma.service.mapperservice.MemberService;
import com.company.scma.service.mapperservice.RoleService;
import com.company.scma.service.mapperservice.UserService;
import com.company.scma.service.validateservice.UserValidateService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBizServiceImpl implements UserBizService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidateService userValidateService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MemberService memberService;

    @Override
    public Result getUser(GetUserDTO getUserDTO) {
        //参数校验
        boolean flag = userValidateService.validateGetUserDTO(getUserDTO);
        if(!flag){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //查询用户
        IPage<TUser> userIPage = userService.getUserByCondition(getUserDTO);
        UserListVO userListVO = GenerateUtil.getUserListVO(userIPage);
        //返回
        return Result.success(userListVO);
    }

    @Override
    public Result getUserDetail(Integer userid) {
        //校验参数
        if(ObjectUtil.isEmpty(userid)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //查询用户信息
        TUser tUser = userService.getUserByUserid(userid);
        //查询角色信息并封装返回结果
        if(ObjectUtil.isEmpty(tUser)){
            return Result.success(new UserDetailVO());
        }
        UserDetailVO userDetailVO = GenerateUtil.getUserDetailVO(tUser);
        Integer roleId = tUser.getRoleId();
        if(ObjectUtil.isNotEmpty(roleId)){
            TRole tRole = roleService.getRoleByRoleId(roleId);
            if(ObjectUtil.isNotEmpty(tRole)){
                RoleListVO myRole = GenerateUtil.getRoleListVOByTRole(tRole);
                userDetailVO.setMyRole(myRole);
            }
        }
        List<TRole> allRole = roleService.getAllRole();
        if (ObjectUtil.isNotEmpty(allRole)) {
            List<RoleListVO> allRoleListVO = allRole.stream().map(GenerateUtil::getRoleListVOByTRole).collect(Collectors.toList());
            userDetailVO.setAllRole(allRoleListVO);
        }
        //返回
        return Result.success(userDetailVO);
    }

    @Override
    public Result checkUserName(String username) {
        TUser tUser = userService.selectUserByUsername(username,null,null);
        if(ObjectUtil.isNotEmpty(tUser)){
            return Result.getResult(ResultEnum.EXIST_USERNAME);
        }
        return Result.success();
    }

    @Override
    @Transactional
    public Result createUser(CreateUserDTO createUserDTO) {
        //数据校验
        Result result = userValidateService.validateCreateUserDTO(createUserDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //密码解密
        
        //生成实体类
        TUser tUser = GenerateUtil.createTUser(createUserDTO);
        //密码加密
        String password = tUser.getPassword();
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        tUser.setPassword(hashPassword);
        //入库
        userService.save(tUser);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result createUserByPartnership(String username, String name, String password, Integer partnershipId, Integer subAccountType) {
        //数据校验
        if(Constant.SubAccountType.MANAGER.equals(subAccountType)){
            Result result = userValidateService.validateCreateUserParam(username,password);
            if(!Result.isSuccess(result)){
                return result;
            }
        }
        //密码解密

        //生成实体类
        TUser tUser = GenerateUtil.getPartnerShipUser(username, name, password, partnershipId);
        //返回
        return Result.success(tUser);
    }

    @Override
    @Transactional
    public Result editUser(EditUserDTO editUserDTO) {
        //参数校验
        Result result = userValidateService.validateEditUserDTO(editUserDTO);
        if(!Result.isSuccess(result)){
            return result;
        }
        //生成实体类
        TUser tUser = GenerateUtil.getTUser(editUserDTO);
        //密码加密
        String password = tUser.getPassword();
        if(ObjectUtil.isNotEmpty(editUserDTO.getPassword())){
            String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            tUser.setPassword(hashPassword);
        }
        //修改member表所属人名称,由于已经失效的会员ownerUserid会被清空，这里不用单独对会员状态进行筛选
        List<TMember> tMemberList = memberService.getMemberByOwnerUserid(tUser.getUserid());
        if(ObjectUtil.isNotEmpty(tMemberList)){
            tMemberList.stream().forEach(tMember -> {
                tMember.setOwnerUsername(tUser.getUsername());
            });
            memberService.saveOrUpdateBatch(tMemberList);
        }
        //保存用户
        userService.saveOrUpdate(tUser);
        //返回
        return Result.success();
    }

    @Override
    @Transactional
    public Result deleteUser(Integer userid) {
        //校验参数
        Result result = userValidateService.validateDeleteUserid(userid);
        if(!Result.isSuccess(result)){
            return result;
        }
        //删除用户
        userService.deleteUserByUserid(userid);
        //返回
        return Result.success();
    }
}
