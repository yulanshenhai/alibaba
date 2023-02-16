package com.xiao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 授权服务器的配置
 *
 * @author xiao
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private TokenStore redisTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 设置Token的存储方式为Redis存储
        // endpoints.tokenStore(redisTokenStore);

        // 配置JWT内容增强器
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(new JwtTokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);

        // 设置Token的存储方式为JWT存储
        endpoints.tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 将授权服务器的配置存储在内存中
        clients.inMemory()
                // 配置授权许可之客户端ID
                .withClient("my-client-id")
                // 配置授权许可之密钥
                .secret(passwordEncoder.encode("my-client-secret"))
                // 配置accessToken的有效期，单位秒
                .accessTokenValiditySeconds(3600)
                // 配置refreshToken的有效期，单位秒
                .refreshTokenValiditySeconds(6000)
                // 开启自动授权: 否则需要在浏览器手动点击授权按钮
                .autoApprove(true)
                // 配置授权成功后跳转的路径
                .redirectUris("http://localhost:8010/login")
                // 配置申请的权限范围
                .scopes("all")
                // 配置授权类型为授权码类型并启用refreshToken功能
                .authorizedGrantTypes("authorization_code", "refresh_token");
    }

    /**
     * 整合SSO功能，必须重写该方法，并进行 `isAuthenticated` 身份认证
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 获取密钥需要身份认证，使用SSO必须配置
        security.tokenKeyAccess("isAuthenticated()");
    }
}