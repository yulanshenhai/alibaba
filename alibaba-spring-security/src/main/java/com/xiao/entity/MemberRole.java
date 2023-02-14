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
@TableName("member_role")
public class MemberRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员-角色中间表主键
     */
    @TableId(value = "member_role_id", type = IdType.AUTO)
    private Integer memberRoleId;

    /**
     * 会员表主键
     */
    private Integer memberId;

    /**
     * 角色表主键
     */
    private Integer roleId;

    /**
     * 首次创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModify;

    /**
     * 会员属性，1:1关系
     */
    private Member member;

    /**
     * 角色属性，1:1关系
     */
    private Role role;
}
