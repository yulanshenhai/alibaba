package com.xiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 会员-角色中间表
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@Data
@TableName("role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色-权限中间表主键
     */
    @TableId(value = "role_permission_id", type = IdType.AUTO)
    private Integer rolePermissionId;

    /**
     * 角色表主键
     */
    private Integer roleId;

    /**
     * 权限表主键
     */
    private Integer permissionId;

    /**
     * 首次创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModify;

    /**
     * 角色属性，1:1关系
     */
    private Role role;

    /**
     * 权限属性，1:1关系
     */
    private Permission permission;
}
