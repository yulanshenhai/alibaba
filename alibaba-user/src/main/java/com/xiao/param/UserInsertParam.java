package com.xiao.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JoeZhou
 * @since 2022-10-25
 */
@Data
public class UserInsertParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String phone;
}
