package com.xiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiao.constant.Format;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员-角色中间表
 * </p>
 *
 * @author xiao
 * @since 2023-02-16
 */
@Data
@TableName("user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户-角色中间表主键
     */
    @TableId(value = "user_role_id", type = IdType.AUTO)
    private Integer userRoleId;

    /**
     * 用户表主键
     */
    private Integer userId;

    /**
     * 角色表主键
     */
    private Integer roleId;

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
