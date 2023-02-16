package com.xiao.controller;

import com.xiao.service.MemberService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xiao
 * @since 2023-02-13
 */
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("select-by-member-id/{memberId}")
    public Object selectByMemberId(@PathVariable("memberId") Integer memberId) {
        return memberService.getById(memberId);
    }

    @GetMapping("get-auth")
    public Object getAuth(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @GetMapping("get-token")
    public Object getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(authorization.indexOf("bearer") + 7);
        // 解析Token: 返回主要信息
        return Jwts.parser()
                .setSigningKey("my_secret".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

}
