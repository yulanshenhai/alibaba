package com.xiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@Data
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员表主键
     */
    @TableId(value = "member_id", type = IdType.AUTO)
    private Integer memberId;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 会员昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 首次创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModify;


}
