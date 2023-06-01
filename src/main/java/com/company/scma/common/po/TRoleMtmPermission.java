package com.company.scma.common.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_role_mtm_permission
 * @author 
 */
@Data
@TableName("T_ROLE_MTM_PERMISSION")
public class TRoleMtmPermission implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Integer serialno;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}