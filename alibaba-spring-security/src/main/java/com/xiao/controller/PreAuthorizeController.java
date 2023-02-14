package com.xiao.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author xiao */
@RestController
public class PreAuthorizeController {

    @PreAuthorize("hasAnyRole('root,admin')")
    @RequestMapping("/insert")
    public String insert() {
        return "insert()方法访问成功";
    }

    @PreAuthorize("hasAnyRole('root,admin,normal')")
    @RequestMapping("/select")
    public String select() {
        return "select()方法访问成功";
    }

    @PreAuthorize("hasAnyRole('root,admin')")
    @RequestMapping("/update")
    public String update() {
        return "update()方法访问成功";
    }

    @PreAuthorize("hasAnyRole('root,admin')")
    @RequestMapping("/delete")
    public String delete() {
        return "delete()方法访问成功";
    }

    @PreAuthorize("hasRole('root')")
    @RequestMapping("/create")
    public String create() {
        return "create()方法访问成功";
    }

    @PreAuthorize("hasRole('root')")
    @RequestMapping("/drop")
    public String drop() {
        return "create()方法访问成功";
    }
}