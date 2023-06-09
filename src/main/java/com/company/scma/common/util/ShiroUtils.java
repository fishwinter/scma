package com.company.scma.common.util;


import com.company.scma.common.po.TUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class ShiroUtils {
    /** 私有构造器 **/
    private ShiroUtils(){}

    //private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

    /**
     * 获取当前用户Session
     * @Author Sans
     * @CreateTime 2019/6/17 17:03
     * @Return SysUserEntity 用户信息
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户登出
     * @Author Sans
     * @CreateTime 2019/6/17 17:23
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     * @Author Sans
     * @CreateTime 2019/6/17 17:03
     * @Return SysUserEntity 用户信息
     */
    public static TUser getUserInfo() {
        return (TUser) SecurityUtils.getSubject().getPrincipal();
    }

//    /**
//     * 删除用户缓存信息
//     * @Author Sans
//     * @CreateTime 2019/6/17 13:57
//     * @Param  username  用户名称
//     * @Param  isRemoveSession 是否删除Session
//     * @Return void
//     */
//    public static void deleteCache(String username, boolean isRemoveSession){
//        //从缓存中获取Session
//        Session session = null;
//        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
//        SysUserEntity sysUserEntity;
//        Object attribute = null;
//        for(Session sessionInfo : sessions){
//            //遍历Session,找到该用户名称对应的Session
//            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//            if (attribute == null) {
//                continue;
//            }
//            sysUserEntity = (SysUserEntity) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
//            if (sysUserEntity == null) {
//                continue;
//            }
//            if (Objects.equals(sysUserEntity.getUsername(), username)) {
//                session=sessionInfo;
//                break;
//            }
//        }
//        if (session == null||attribute == null) {
//            return;
//        }
//        //删除session
//        if (isRemoveSession) {
//            redisSessionDAO.delete(session);
//        }
//        //删除Cache，在访问受限接口时会重新授权
//        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
//        Authenticator authc = securityManager.getAuthenticator();
//        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
//    }
}
