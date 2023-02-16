package com.xiao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 授权服务器的配置
 *
 * @author xiao
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenStore redisTokenStore;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 将配置存储在内存中
        clients.inMemory()
                //配置 client-id
                .withClient("my-client-id")
                // 配置client-secret
                .secret(passwordEncoder.encode("my-client-secret"))
                // 配置accessToken的有效期，单位秒
                .accessTokenValiditySeconds(3600)
                // 配置refreshToken的有效期，单位秒
                .refreshTokenValiditySeconds(6000)
                // 自动授权: 否则需要在浏览器手动点击授权按钮
                //.autoApprove(true)
                // 配置授权成功后跳转的路径
                .redirectUris("http://www.baidu.com")
                // 配置申请的权限范围
                .scopes("all")
                // 配置授权类型为授权码类型和refreshToken
                .authorizedGrantTypes("authorization_code", "refresh_token");
    }

}