package com.xiao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.entity.User;
import com.xiao.mapper.UserMapper;
import com.xiao.param.UserUpdateParam;
import com.xiao.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiao
 * @since 2023-02-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("必要参数为空");
        }
        // 构建查询条件包装器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 构建查询条件
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<User> selectLikeUsername(String username) {

        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("必要参数为空");
        }

        // 构建查询条件包装器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 构建查询条件
        queryWrapper.like("username", username);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public int updateLikeUsername(UserUpdateParam userUpdateParam) {

        // 必要参数判断
        String username = userUpdateParam.getUsername();
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("必要参数为空");
        }

        // 构建User参数：账号只作为修改条件而不参与修改，主键不参与修改
        User user = new User();
        user.setUsername(null);
        user.setUserId(null);

        // 构建修改条件包装器
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        // 构建修改条件
        updateWrapper.like("username", username);

        // 若无修改字段则会爆发SQL语句异常，可以使用set保护
        updateWrapper.set("last_modify", new Date());

        // 发送SQL语句
        return userMapper.update(user, updateWrapper);
    }

    @Override
    public Page<User> paging(Long page, Long size) {
        return userMapper.selectPage(new Page<>(page, size), null);
    }
}
