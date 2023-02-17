package com.xiao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiao.entity.User;
import com.xiao.param.UserInsertParam;
import com.xiao.param.UserUpdateParam;
import com.xiao.service.UserService;
import com.xiao.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiao
 * @since 2022-10-25
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("/select-by-user-id")
    public Result selectByUserId(@RequestParam("user-id") Integer userId) {
        log.info("请求：查询 {} 号用户记录", userId);
        User user = userService.getById(userId);
        log.info("响应：查询到 {} 用户记录：{}", userId, user);
        return user != null ? Result.ok(user) : Result.fail(-1, "查无此人");
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody UserInsertParam userInsertParam) {
        log.info("请求：添加一条用户记录");
        User user = new User();
        BeanUtils.copyProperties(userInsertParam, user);
        log.info("参数：{}", user);
        boolean insertResult = userService.save(user);
        log.info("响应：{}", insertResult ? "插入成功" : "插入失败");
        return insertResult ? Result.ok(user) : Result.fail(-1, "插入失败");
    }

    @PostMapping("/update-by-user-id")
    public Result updateByUserId(@RequestBody UserUpdateParam userUpdateParam) {
        log.info("请求：根据主键修改一条用户记录");
        User user = new User();
        BeanUtils.copyProperties(userUpdateParam, user);
        log.info("参数：{}", user);
        boolean updateResult = userService.updateById(user);
        log.info("响应：{}", updateResult ? "修改成功" : "修改失败");
        return updateResult ? Result.ok(user) : Result.fail(-1, "修改失败");
    }

    @GetMapping("/list")
    public Result list() {
        log.info("请求：全查用户记录");
        List<User> users = userService.list();
        log.info("响应：查到全部用户记录: {}", users);
        return !users.isEmpty() ? Result.ok(users) : Result.fail(-1, "当前不存在任何用户记录");
    }

    @PostMapping("/delete-by-user-id")
    public Result deleteById(@RequestParam("user-id") Integer userId) {
        log.info("请求：删除 {} 号用户", userId);
        boolean removeResult = userService.removeById(userId);
        log.info("响应：{}", removeResult ? "删除成功" : "删除失败");
        return removeResult ? Result.ok() : Result.fail(-1, "删除失败");
    }

    @GetMapping("/select-like-username")
    public Result selectLikeUsername(@RequestParam String username) {
        log.info("请求：通过账号 {} 来模糊查询用户记录", username);
        List<User> users = userService.selectLikeUsername(username);
        log.info("响应：查到符合条件的用户记录: {}", users);
        return !users.isEmpty() ? Result.ok(users) : Result.fail(-1, "当前不存在任何符合条件的用户记录");
    }

    @PostMapping("/update-like-username")
    public Result updateLikeUsername(@RequestBody UserUpdateParam userUpdateParam) {
        log.info("请求：通过账号 {} 来模糊修改用户记录", userUpdateParam);
        int updateResult = userService.updateLikeUsername(userUpdateParam);
        log.info("响应：修改了 {} 条记录", updateResult);
        return updateResult > 0 ? Result.ok() : Result.fail(-1, "修改失败");
    }

    @GetMapping("/paging")
    public Result paging(@RequestParam(required = false, defaultValue = "1") Long page,
                         @RequestParam(required = false, defaultValue = "5") Long size) {
        log.info("请求：分页查询用户记录，显示第 {} 页，每页 {} 条", page, size);
        Page<User> userPage = userService.paging(page, size);
        log.info("响应：分页查到部分用户的记录: {}", userPage);
        return userPage != null ? Result.ok(userPage) : Result.fail(-1, "查询失败");
    }

    @GetMapping("/select-by-username")
    public Result selectByUsername(@RequestParam String username) {
        log.info("请求：通过账号 {} 来查询用户记录", username);
        User user = userService.selectByUsername(username);
        log.info("响应：查到符合条件的用户记录: {}", user);
        return null != user ? Result.ok(user) : Result.fail(-1, "当前不存在任何符合条件的用户记录");
    }
}
