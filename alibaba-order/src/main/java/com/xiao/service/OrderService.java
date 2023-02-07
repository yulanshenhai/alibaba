package com.xiao.service;

import com.xiao.param.OrderInsertParam;
import com.xiao.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xiao
 * @since 2022-10-25
 */
public interface OrderService extends IService<Order> {

    /**
     * 添加一条Order表记录
     *
     * @param orderInsertParam 添加订单业务实体参数
     * @return 影响条目数
     */
    int insert(OrderInsertParam orderInsertParam);

}
