package com.xiao.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiao
 */
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken,
                                     OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> tokenExt = new HashMap<>(2);
        tokenExt.put("author", "xiao");
        tokenExt.put("logo", "a.jpg");
        // 将自定义的Map类型的Token负载加入到Token中
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(tokenExt);
        return oAuth2AccessToken;
    }
}