package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_permission
 * @author 
 */
@Data
@TableName("T_PERMISSION")
public class TPermission implements Serializable {
    /**
     * 权限id
     */
    @TableId
    private Integer permissionId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限描述
     */
    private String introduction;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;
    
    //菜单名排序
    private Integer menuSort;
    
    //权限排序
    private Integer permissionSort;

    private static final long serialVersionUID = 1L;
}