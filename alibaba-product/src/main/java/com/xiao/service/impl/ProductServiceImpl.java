package com.xiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiao.entity.Product;
import com.xiao.mapper.ProductMapper;
import com.xiao.service.ProductService;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xiao
 * @since 2022-10-25
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int reduceInventory(Integer id, Integer number) throws TransactionException {

        // 通过商品主键查询该商品记录
        Product product = productMapper.selectById(id);

        // 查询当前产品的库存量
        Integer currentProductStock = product.getProductStock();

        // 在内存中扣减库存
        int reduceResult = currentProductStock - number;
        if (reduceResult < 0) {
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            return -1;
        }

        // 重新设置商品库存
        product.setProductStock(reduceResult);

        // 修改商品信息
        return productMapper.updateById(product);
    }
}
