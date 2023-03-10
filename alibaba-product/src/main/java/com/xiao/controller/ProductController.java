package com.xiao.controller;

import com.xiao.entity.Product;
import com.xiao.service.ProductService;
import com.xiao.util.Result;
import io.seata.core.exception.TransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiao
 * @since 2022-10-25
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/select-by-id")
    public Result selectById(@RequestParam Integer id) {
        log.info("请求：查询 {} 号商品记录", id);
        Product product = productService.getById(id);
        log.info("响应：查询到 {} 商品记录：{}", id, product);
        return product != null ? Result.ok(product) : Result.fail(-1, "查无此商品");
    }

    @PostMapping("/reduce-inventory")
    public Result reduceInventory(@RequestParam Integer id,
                                  @RequestParam Integer number) throws TransactionException {
        log.info("请求：扣减 {} 号商品的库存，扣减 {} 个", id, number);
        int reduceResult = productService.reduceInventory(id, number);
        log.info(reduceResult > 0 ? "扣减成功" : "扣减失败");
        return reduceResult > 0 ? Result.ok() : Result.fail(-1, "扣减失败");
    }

}
