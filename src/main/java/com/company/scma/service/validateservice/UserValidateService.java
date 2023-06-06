package com.company.scma.service.validateservice;

import com.company.scma.common.dto.CreateUserDTO;
import com.company.scma.common.dto.EditUserDTO;
import com.company.scma.common.dto.GetUserDTO;
import com.company.scma.common.vo.Result;

public interface UserValidateService {
    
    public Result validateCreateUserDTO(CreateUserDTO createUserDTO);
    
    public boolean validateGetUserDTO(GetUserDTO getUserDTO);
    
    public Result validateEditUserDTO(EditUserDTO editUserDTO);
}
