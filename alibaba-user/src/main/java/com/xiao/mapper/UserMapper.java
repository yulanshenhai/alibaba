package com.xiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.entity.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xiao
 * @since 2023-02-16
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
