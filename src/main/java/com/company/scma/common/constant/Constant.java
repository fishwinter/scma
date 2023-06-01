package com.company.scma.common.constant;

//常数类
public interface Constant {
    
    //是否
    public interface Judge{
        Integer YES = 1;
        Integer NO = 0;
    }
    
    //数据库字段名
    public interface ColumnName{
        String DELETEFLAG = "DELETEFLAG";
        String ROLE_ID = "ROLE_ID";
        String PERMISSION_ID = "PERMISSION_ID";
        String USERNAME = "USERNAME";
        String STATUS = "STATUS";
    }
    
    //常用常数
    public interface Common{
        //session存放的key
        String SESSION_KEY = "scma-user";
        //登录拦截器忽略的url
        String[] IGNORE_URL = {"/login"};
    }
    
    //用户类型
    public interface UserType{
        Integer COMMON_USER = 1;
        Integer SON_ACCOUNT_USER = 2;
    }
    
    //性别
    public interface Sex{
        Integer MALE = 1;
        Integer FEMALE = 2;
    }
}
