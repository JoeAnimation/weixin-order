package com.longyx.product.client;

import com.longyx.product.common.DecreaseStockInput;
import com.longyx.product.common.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.Longyx
 * @date 2020年01月17日 23:58
 */
@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public String productMsg() {
        return null;
    }

    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }
}
