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
 * 用户表
 * </p>
 *
 * @author xiao
 * @since 2023-02-16
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户真实姓名
     */
    private String realName;

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
