package com.xiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiao.entity.Product;
import io.seata.core.exception.TransactionException;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author xiao
 * @since 2022-10-25
 */
public interface ProductService extends IService<Product> {


    /**
     * 扣减商品库存
     *
     * @param id     商品主键
     * @param number 扣减的数量
     * @return 影响条目数
     */
    int reduceInventory(Integer id, Integer number) throws TransactionException;

}
