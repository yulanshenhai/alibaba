package com.xiao.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiao
 * @since 2022-10-25
 */
@Data
public class OrderInsertDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private Integer productId;
    private Integer number;
}
