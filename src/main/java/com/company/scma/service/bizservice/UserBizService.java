package com.company.scma.service.bizservice;

import com.company.scma.common.dto.CreateUserDTO;
import com.company.scma.common.dto.EditUserDTO;
import com.company.scma.common.dto.GetUserDTO;
import com.company.scma.common.vo.Result;

public interface UserBizService {
    public Result getUser(GetUserDTO getUserDTO);
    
    public Result getUserDetail(Integer userid);
    
    public Result checkUserName(String username);
    
    public Result createUser(CreateUserDTO createUserDTO);
    
    public Result createUserByPartnership(String username,String name, String password,Integer partnershipId,Integer subAccountType);
    
    public Result editUser(EditUserDTO editUserDTO);
    
    public Result deleteUser(Integer userid);
    
}
