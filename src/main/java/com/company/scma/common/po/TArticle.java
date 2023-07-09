package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_article
 * @author 
 */
@Data
@TableName("T_ARTICLE")
public class TArticle implements Serializable {
    /**
     * 文章id
     */
    @TableId
    private Integer articleId;

    /**
     * 作者id
     */
    private Integer authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 刊期
     */
    private String frequency;

    /**
     * 编辑名称
     */
    private String editorName;

    /**
     * 文件url
     */
    private String fileUrl;

    /**
     * 创建时间
     */
    private Date buildDate;

    /**
     * 创建用户id
     */
    private Integer buildUserid;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改用户id
     */
    private Integer modifyUserid;

    /**
     * 删除标记：0-删除，1-正常
     */
    private Integer deleteflag;

    private static final long serialVersionUID = 1L;
}