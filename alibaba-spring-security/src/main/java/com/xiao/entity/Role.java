package com.xiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色表主键
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleTitle;

    /**
     * 角色描述
     */
    private String roleInfo;

    /**
     * 首次创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModify;


}
