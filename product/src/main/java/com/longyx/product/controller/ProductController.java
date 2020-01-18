package com.longyx.product.controller;

import com.longyx.product.VO.ProductInfoVO;
import com.longyx.product.VO.ProductVO;
import com.longyx.product.VO.ResultVO;
import com.longyx.product.common.DecreaseStockInput;
import com.longyx.product.common.ProductInfoOutput;
import com.longyx.product.dataobject.ProductCategory;
import com.longyx.product.dataobject.ProductInfo;
import com.longyx.product.service.CategoryService;
import com.longyx.product.service.ProductService;
import com.longyx.product.utils.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品
 * @author Mr.Longyx
 * @date 2020年01月11日 17:19
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1.查询所有在架的商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据
     * @author Mr.Longyx
     * @date 2020/1/11 17:22
     */
    @RequestMapping("/list" )
    public ResultVO<ProductVO> list(HttpServletRequest request){
        List<ProductInfo> productInfoList = productService.findUpAll();
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        //从数据库中查询类目
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);
        
        List<ProductVO>  productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategories){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVoUtil.success(productVOList);
    }

    /**
     * 获取商品列表(提供给订单服务使用)
     * @author Mr.Longyx
     * @date 2020/1/12 11:03
     * @param productIdList
     * @return java.util.List<com.longyx.product.dataobject.ProductInfo>
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody  List<String> productIdList){

        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock" )
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        productService.decreaseStock(decreaseStockInputList);
    }
}
