package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.scma.common.constant.Constant;
import com.company.scma.common.dto.GetUserDTO;
import com.company.scma.common.po.TUser;
import com.company.scma.mapper.UserMapper;
import com.company.scma.service.mapperservice.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TUser> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public TUser selectUserByUsername(String username) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.USERNAME, username);
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.STATUS, Constant.Judge.YES);
        List<TUser> tUserList = userMapper.selectList(queryWrapper);
        if (ObjectUtil.isNotEmpty(tUserList)) {
            return tUserList.get(0);
        }
        return null;
    }

    @Override
    public IPage<TUser> getUserByCondition(GetUserDTO getUserDTO) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.orderByDesc(Constant.ColumnName.USERID);

        if (StringUtils.isNotEmpty(getUserDTO.getUsername())) {
            queryWrapper.eq(Constant.ColumnName.USERNAME, getUserDTO.getUsername());
        }

        if (StringUtils.isNotEmpty(getUserDTO.getName())) {
            queryWrapper.eq(Constant.ColumnName.NAME, getUserDTO.getName());
        }

        if (StringUtils.isNotEmpty(getUserDTO.getTel())) {
            queryWrapper.eq(Constant.ColumnName.TEL, getUserDTO.getTel());
        }

        if (ObjectUtil.isNotEmpty(getUserDTO.getStartDate())) {
            queryWrapper.ge(Constant.ColumnName.BUILD_DATE, getUserDTO.getStartDate());
        }

        if (ObjectUtil.isNotEmpty(getUserDTO.getEndDate())) {
            queryWrapper.le(Constant.ColumnName.BUILD_DATE, getUserDTO.getEndDate());
        }

        Page<TUser> page = Page.of(getUserDTO.getCurrentPage(), getUserDTO.getPageSize(), 0, true);
        IPage<TUser> tUserIPage = userMapper.selectPage(page, queryWrapper);
        return tUserIPage;
    }

    @Override
    public List<TUser> selectUserByRoleId(Integer roleId) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG, Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.STATUS, Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.ROLE_ID,roleId);
        List<TUser> tUserList = userMapper.selectList(queryWrapper);
        return tUserList;
    }

    @Override
    public void deleteUserByUserid(Integer userid) {
        UpdateWrapper<TUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(Constant.ColumnName.DELETEFLAG,Constant.Judge.NO);
        updateWrapper.eq(Constant.ColumnName.USERID,userid);
        userMapper.update(null,updateWrapper);
    }

    @Override
    public TUser getUserByUserid(Integer userid) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.USERID,userid);
        List<TUser> tUserList = userMapper.selectList(queryWrapper);
        if(ObjectUtil.isNotEmpty(tUserList)){
            return tUserList.get(0);
        }
        return null;
    }

    @Override
    public List<TUser> getUserByTypeAndBuildId(Integer userType, Integer buildId) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ColumnName.DELETEFLAG,Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.STATUS, Constant.Judge.YES);
        queryWrapper.eq(Constant.ColumnName.TYPE,userType);
        queryWrapper.orderByDesc(Constant.ColumnName.USERID);
        if(Constant.UserType.COMMON_USER.equals(userType)){
            queryWrapper.eq(Constant.ColumnName.BUILD_USERID,buildId);
        }else {
            queryWrapper.eq(Constant.ColumnName.BUILD_PARTNERSHIPID,buildId);
        }
        List<TUser> tUserList = userMapper.selectList(queryWrapper);
        return tUserList;
    }
}
