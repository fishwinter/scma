package com.company.scma.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.company.scma.common.dto.CreateRoleDTO;
import com.company.scma.service.validateservice.CommonValidateService;
import com.company.scma.service.validateservice.RoleValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleValidateServiceImpl implements RoleValidateService {
    
    @Autowired
    private CommonValidateService commonValidateService;
    
    @Override
    public boolean validateCreateRoleDTO(CreateRoleDTO createRoleDTO) {
        if(ObjectUtil.isEmpty(createRoleDTO)){
            return false;
        }
        return commonValidateService.validateAnnotation(createRoleDTO);
    }
}
