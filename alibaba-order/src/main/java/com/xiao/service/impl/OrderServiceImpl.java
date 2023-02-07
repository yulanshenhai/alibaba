package com.xiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.entity.Order;
import com.xiao.entity.Product;
import com.xiao.feign.ProductFeign;
import com.xiao.mapper.OrderMapper;
import com.xiao.param.OrderInsertParam;
import com.xiao.service.OrderService;
import com.xiao.util.JacksonUtil;
import com.xiao.util.Result;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiao
 * @since 2022-10-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductFeign productFeign;

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public int insert(OrderInsertParam orderInsertParam) {

        // 获取商品主键
        Integer productId = orderInsertParam.getProductId();

        // 调用商品微服务的的 `按主键查询商品信息` 方法
        Result result = productFeign.selectById(productId);
        if (result.getCode() <= 0) {
            throw new RuntimeException("商品信息查询失败");
        }

        // 将结果中的Product实体数据解析出来
        Product product = JacksonUtil.parseData(result.getData(), Product.class);

        // 构建Order实体
        Order order = new Order();
        BeanUtils.copyProperties(orderInsertParam, order);
        order.setProductName(product.getProductName());

        // 调用添加订单的数据方法
        int insertResult = orderMapper.insert(order);
        if (insertResult < 0) {
            throw new RuntimeException("订单添加失败");
        }

        // 添加成功后，进行扣减库存
        Result reduceResult = productFeign.reduceInventory(productId, order.getNumber());
        if (reduceResult.getCode() < 0) {
            throw new RuntimeException("扣减库存失败");
        }

        // 添加一条订单记录
        return insertResult;
    }
}
