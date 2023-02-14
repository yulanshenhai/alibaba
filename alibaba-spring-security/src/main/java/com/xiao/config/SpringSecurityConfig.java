package com.xiao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xiao
 */
@Configuration
public class SpringSecurityConfig {

    /**
     * 重写SpringSecurity登录逻辑时，Spring容器中必须存在一个 `PasswordEncoder` 的Bean实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}