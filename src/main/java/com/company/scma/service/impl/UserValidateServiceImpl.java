package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.constant.ResultEnum;
import com.company.scma.common.dto.CreateUserDTO;
import com.company.scma.common.dto.EditUserDTO;
import com.company.scma.common.dto.GetUserDTO;
import com.company.scma.common.po.TUser;
import com.company.scma.common.vo.Result;
import com.company.scma.service.mapperservice.UserService;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.UserValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidateServiceImpl implements UserValidateService {
    @Autowired
    private CommonValidateService commonValidateService;
    
    @Autowired
    private UserService userService;
    
    @Override
    public Result validateCreateUserDTO(CreateUserDTO createUserDTO) {
        if(ObjectUtil.isEmpty(createUserDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        if(commonValidateService.validateAnnotation(createUserDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }
        
        if(createUserDTO.getUsername().contains("-")){
            return Result.getResult(ResultEnum.ERROR_USERNAME);
        }
        
        //查询数据库，另外这里由于没办法引入redis做锁，所以直接在数据库中对username建立了唯一索引
        TUser tUser = userService.selectUserByUsername(createUserDTO.getUsername());
        if(ObjectUtil.isNotEmpty(tUser)){
            return Result.getResult(ResultEnum.EXIST_USERNAME);
        }
        return Result.success();
    }

    @Override
    public Result validateCreateUserParam(String username, String password) {
        if(ObjectUtil.isEmpty(username) || ObjectUtil.isEmpty(password)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(username.contains("-")){
            return Result.getResult(ResultEnum.ERROR_MANAGER_USERNAME);
        }

        TUser tUser = userService.selectUserByUsername(username);
        if(ObjectUtil.isNotEmpty(tUser)){
            return Result.getResult(ResultEnum.EXIST_MANAGER_USERNAME);
        }
        return Result.success();
    }

    @Override
    public boolean validateGetUserDTO(GetUserDTO getUserDTO) {
        if(ObjectUtil.isEmpty(getUserDTO)){
            return false;
        }
        return commonValidateService.validateAnnotation(getUserDTO);
    }

    @Override
    public Result validateEditUserDTO(EditUserDTO editUserDTO) {
        if(ObjectUtil.isEmpty(editUserDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(commonValidateService.validateAnnotation(editUserDTO)){
            return Result.getResult(ResultEnum.ERROR_PARAM);
        }

        if(editUserDTO.getUsername().contains("-")){
            return Result.getResult(ResultEnum.ERROR_USERNAME);
        }

        TUser tUser = userService.selectUserByUsername(editUserDTO.getUsername());
        if(ObjectUtil.isNotEmpty(tUser) && tUser.getUserid() != editUserDTO.getUserid()){
            return Result.getResult(ResultEnum.EXIST_USERNAME);
        }
        return Result.success();
    }
}
