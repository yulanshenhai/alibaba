package com.xiao.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping("get-auth-message")
    public Map<String,Object> getAuthMessage(Authentication authentication,
                           HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<>(3);

        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(authorization.indexOf("bearer") + 7);

        resultMap.put("authentication", authentication.getPrincipal());
        resultMap.put("token", token);
        return resultMap;
    }

    @GetMapping("parse-token")
    public Object parseToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(authorization.indexOf("bearer") + 7);

        return Jwts.parser()
                .setSigningKey("my_secret".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
