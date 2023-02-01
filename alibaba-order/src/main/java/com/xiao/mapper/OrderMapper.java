package com.xiao.mapper;

import com.xiao.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author xiao
 * @since 2022-10-25
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}

