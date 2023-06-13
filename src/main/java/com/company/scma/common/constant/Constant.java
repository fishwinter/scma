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
        String USERID = "USERID";
        String NAME = "NAME";
        String TEL = "TEL";
        String BUILD_DATE = "BUILD_DATE";
        String MEMBER_NAME = "MEMBER_NAME";
        String SERIALNO = "SERIALNO";
        String ITEM_CODE = "ITEM_CODE";
        String TYPE = "TYPE";
        String OWNER_USERID = "OWNER_USERID";
        String MEMBER_ID = "MEMBER_ID";
        String CONTACTS = "CONTACTS";
        String MEMBER_TYPE_ID = "MEMBER_TYPE_ID";
        String OPERATION_ID = "OPERATION_ID";
        String OPERATION_NAME = "OPERATION_NAME";
        String DIRECTOR = "DIRECTOR";
        String CONTACT_TEL = "CONTACT_TEL";
        String PARTNERSHIP_ID = "PARTNERSHIP_ID";
        String PARTNERSHIP_NAME = "PARTNERSHIP_NAME";
        String CONTACT_NAME = "CONTACT_NAME";
        String CUSTCODE = "CUSTCODE";
        String BUILD_USERID = "BUILD_USERID";
        String BUILD_PARTNERSHIPID = "BUILD_PARTNERSHIPID";
        String CUSTVALUE = "CUSTVALUE";
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
        Integer SUB_ACCOUNT_USER = 2;
    }
    
    //性别
    public interface Sex{
        Integer MALE = 1;
        Integer FEMALE = 2;
    }
    
    //实体类型
    public interface ItemType{
        Integer PROVINCE = 1;
        Integer CITY = 2;
    }
    
    public interface MemberStatus{
        Integer RELEASED_PUBLIC_RESOURCE = 1;
        Integer NORMAL = 2;
        Integer RELEASED_MEMBER_DATABASE = 2;
    }
    
    public interface OperationStatus{
        Integer NOT_STARTED = 0;
        Integer NORMAL = 1;
        Integer FINISH = 2;
    }
    
    public interface SubAccountType {
        Integer MANAGER = 1;
        Integer SUB_ACCOUNT = 2;
    }
    
    public interface SysConfigCustCode{
        String PARTNERSHIP_TYPE = "PARTNERSHIP_TYPE";
        String PARTNERSHIP_PROJECT_TYPE = "PARTNERSHIP_PROJECT_TYPE";
        String SUSPENDED_TERM = "SUSPENDED_TERM";
        String RELEASE_TERM = "RELEASE_TERM";
        String RELEASE_WAY = "RELEASE_WAY";
    }
}
