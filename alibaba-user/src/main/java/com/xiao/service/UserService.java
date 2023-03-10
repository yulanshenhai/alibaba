package com.xiao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.entity.User;
import com.xiao.param.UserUpdateParam;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xiao
 * @since 2023-02-16
 */
public interface UserService extends IService<User> {

    /**
     * 通过账号来精准查询用户记录
     * @param username 账号
     * @return 符合条件的用户记录
     */
    User selectByUsername(String username);

    /**
     * 通过账号来模糊查询用户记录
     * @param username 账号
     * @return 符合条件的用户记录
     */
    List<User> selectLikeUsername(String username);

    /**
     * 通过账号模糊修改用户记录，
     * user中的null值属性，主键属性和条件字段属性不参与修改过程
     *
     * @param userUpdateParam 用户实体，username属性必传
     * @return 影响条目数
     */
    int updateLikeUsername(UserUpdateParam userUpdateParam);

    /**
     * 分页查询数据
     * @param page 显示第几页
     * @param size 每页多少条
     * @return 分页数据
     */
    Page<User> paging(Long page, Long size);
}
