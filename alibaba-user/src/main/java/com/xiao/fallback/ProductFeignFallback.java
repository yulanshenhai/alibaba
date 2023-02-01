package com.xiao.fallback;

import com.xiao.feign.ProductFeign;
import com.xiao.util.Result;
import org.springframework.stereotype.Component;

/**
 * @author xiao
 */

@Component
public class ProductFeignFallback implements ProductFeign {

    @Override
    public Result selectById(Integer id) {
        return Result.fail(0, "远程调用失败");
    }
}
