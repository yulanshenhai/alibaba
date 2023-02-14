package com.xiao.controller;

import com.xiao.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("select-by-id")
    public Object selectById(Integer id){
        return memberService.getById(id);
    }
}
