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
 * 权限表
 * </p>
 *
 * @author xiao
 * @since 2023-02-16
 */
@Data
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限表主键
     */
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionTitle;

    /**
     * 权限描述
     */
    private String permissionInfo;

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
