package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateRoleDTO;
import com.company.scma.common.dto.EditRoleDTO;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.UserService;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.RoleValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleValidateServiceImpl implements RoleValidateService {
    
    @Autowired
    private CommonValidateService commonValidateService;
    @Autowired
    private UserService userService;
    
    @Override
    public boolean validateCreateRoleDTO(CreateRoleDTO createRoleDTO) {
        if(ObjectUtil.isEmpty(createRoleDTO)){
            return false;
        }
        return commonValidateService.validateAnnotation(createRoleDTO);
    }

    @Override
    public boolean validateEditRoleDTO(EditRoleDTO editRoleDTO) {
        if(ObjectUtil.isEmpty(editRoleDTO)){
            return false;
        }
        return commonValidateService.validateAnnotation(editRoleDTO);
    }

    @Override
    public Result validateDeleteRoleId(Integer roleId) {
        if(ObjectUtil.isEmpty(roleId)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        //查询当前role是否有对应的用户
        List<TUser> tUserList = userService.selectUserByRoleId(roleId);
        if(ObjectUtil.isNotEmpty(tUserList)){
            return Result.getResult(ResultEnum.DELETED_ROLE_BIND_USER);
        }
        return Result.success();
    }
}
