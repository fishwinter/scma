package com.company.scma.common.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {
    SUCCESS(200,"success"),
    
    //用户登录错误
    ERROR_USERNAME_OR_PASSWORD(401,"用户名或密码错误"),
    NO_LOGIN(402,"用户未登录"),
    ERROR_PARAM(403,"参数错误"),
    NO_PERMISSION(405,"没有权限"),
    ERROR_PASSWORD(406,"原密码错误"),
    
    //用户模块错误
    EXIST_USERNAME(411,"用户名已存在"),
    ERROR_USERNAME(412,"用户名存在非法字符"),
    EXIST_LINKED_MEMBER(413,"该用户下存在有效的会员"),
    
    //角色模块错误
    DELETED_ROLE_BIND_USER(421,"删除的角色有绑定的用户"),
    
    //会员模块错误
    EXIST_MEMBER_NAME(431,"会员名已存在"),
    ERROR_MEMBER_ID(432,"会员id错误"),
    USER_CANNOT_OPERATE_MEMBER(433,"当前用户无法编辑该会员"),
    EXIST_MEMBER_TYPE_MEBER(434,"当前会员类型存在已有会员"),
    
    //合作企业模块错误
    ERROR_OPERATION_STATUS(441,"绑定活动状态错误"),
    ERROR_MANAGER_USERNAME(442,"管理员账号名存在非法字符"),
    EXIST_MANAGER_USERNAME(443,"管理员账号名已存在"),
    EXIST_PARTNERSHIP_NAME(445,"合作企业名称已存在"),
    EXIST_MEMBER(446,"当前合作企业下存在未释放的会员"),

    //供应商模块错误
    EXIST_SUPPLIER_NAME(451,"供应商名称已存在"),
    
    //其他错误
    UNKNOWN_ERROR(500,"未知错误");
    ;
    
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
