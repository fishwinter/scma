package com.company.scma.common.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_case
 * @author 
 */
@Data
@TableName("T_CASE")
public class TCase implements Serializable {
    /**
     * 案例id
     */
    @TableId
    private Integer caseId;

    /**
     * 标题
     */
    private String title;

    /**
     * 案列类别id
     */
    private Integer typeId;

    /**
     * 文件url
     */
    private String fileUrl;

    /**
     * 摘要
     */
    private String caseAbstract;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 字数
     */
    private Integer numOfWords;

    /**
     * 备注
     */
    private String remark;

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