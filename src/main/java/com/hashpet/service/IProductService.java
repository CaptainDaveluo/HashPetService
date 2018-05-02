package com.hashpet.service;


import com.hashpet.pojo.Product;
import com.hashpet.pojo.ProductSellDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IProductService {
    public Product queryProductById(Integer pid);
    public void    createNewProduct(Product pro);
    public void    modifyProduct(Product pro);
    public void    dropProduct(Product pro);
    public void    createProductSellDetail(ProductSellDetail detail);
    public List<ProductSellDetail> queryByParams(Map<String,Object> params);
    public List<ProductSellDetail> queryByPriority(Integer limitNum);
    public List<ProductSellDetail> queryByOrderNum(String orderNum);
    public List<ProductSellDetail> queryByRandom(Integer limitNum);
}
