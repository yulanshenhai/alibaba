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
 * 订单表
 * </p>
 *
 * @author xiao
 * @since 2023-02-16
 */
@Data
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 下单用户名
     */
    private String username;

    /**
     * 商品表外键
     */
    private Integer productId;

    /**
     * 商品名（冗余）
     */
    private String productName;

    /**
     * 购买数量
     */
    private Integer number;

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
