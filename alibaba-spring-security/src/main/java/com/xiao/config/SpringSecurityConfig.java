package com.xiao.config;

import com.xiao.handler.MyAuthenticationFailureHandler;
import com.xiao.handler.MyAuthenticationSuccessHandler;
import com.xiao.handler.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author xiao
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 登录表单配置
        http.formLogin()
                // 重写登录页面
                .loginPage("/login.html")
                // 将 `/login` 请求视为登录请求，去执行自定义的登录逻辑
                // 该值和表单的action值保持一致
                .loginProcessingUrl("/login")
                // 该值和表单中账号控件的name值保持一致，默认username
                .usernameParameter("uname")
                // 该值和表单中密码控件的name值保持一致，默认password
                .passwordParameter("pwd")
                // 登录成功时执行MyAuthenticationSuccessHandler处理器
                .successHandler(new MyAuthenticationSuccessHandler())
                // 登录失败时执行MyAuthenticationFailureHandler处理器
                .failureHandler(new MyAuthenticationFailureHandler());

        // 授权认证配置
        http.authorizeRequests()
                // 登录页面放行
                .antMatchers("/login.html").permitAll()
                // 其余全部请求必须被认证: 必须登陆后访问
                .anyRequest().authenticated();

        // 关闭CSRF防护
        http.csrf().disable();

        // 登出配置
        http.logout()
                // 发送 `/logout` 请求即可退出登录，默认 `/logout`
                .logoutUrl("/logout")
                // 退出登录成功时执行MyLogoutSuccessHandler处理器
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                // 删除Cookie中的JSESSIONID，否则连接一直维持，不算注销
                .deleteCookies("JSESSIONID")
                // 使HttpSession失效
                .invalidateHttpSession(true);

        // rememberMe配置
        http.rememberMe()
                // 该值和表单中复选框控件的name值保持一致，默认remember-me
                .rememberMeParameter("remember-me")
                // token过期时间，单位秒，默认2周
                .tokenValiditySeconds(300)
                // 自定义登录逻辑类
                .userDetailsService(userDetailsService)
                // token仓库
                .tokenRepository(persistentTokenRepository());

    }

    /**
     * 重写SpringSecurity登录逻辑时，Spring容器中必须存在一个 `PasswordEncoder` 的Bean实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 设置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}