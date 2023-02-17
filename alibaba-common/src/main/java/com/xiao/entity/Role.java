package com.xiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiao.constant.Format;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author xiao
 * @since 2023-02-16
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
    @JsonFormat(pattern= Format.Date, timezone = Format.Timezone)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @JsonFormat(pattern= Format.Date, timezone = Format.Timezone)
    private Date lastModify;


}
