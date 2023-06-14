package com.company.scma.service.mapperservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.scma.common.dto.GetUserDTO;
import com.company.scma.common.po.TUser;

import java.util.List;

public interface UserService extends IService<TUser> {
    public TUser selectUserByUsername(String username);
    
    public IPage<TUser> getUserByCondition(GetUserDTO getUserDTO);
    
    public List<TUser> selectUserByRoleId(Integer roleId);
    
    public void deleteUserByUserid(Integer userid);
    
    public TUser getUserByUserid(Integer userid);
    
    public List<TUser> getUserByTypeAndBuildId(Integer userType,Integer buildId,Integer status);
    
    public List<TUser> getUserByTypeAndBuildId(Integer userType,List<Integer> buildIdList);
    
    public List<TUser> fuzzGetTUserByUsername(String username);
}
