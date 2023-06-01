package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateUserDTO;
import com.company.scma.common.vo.Result;

public interface UserBizService {
    public Result createUser(CreateUserDTO createUserDTO);
    
}
