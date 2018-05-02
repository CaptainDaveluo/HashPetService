package com.hashpet.service.implement;

import com.hashpet.dao.ProductMapper;
import com.hashpet.dao.ProductSellDetailMapper;
import com.hashpet.pojo.Product;
import com.hashpet.pojo.ProductSellDetail;
import com.hashpet.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductService implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSellDetailMapper sellDetailMapper;

    public Product queryProductById(Integer pid) {
        return productMapper.selectByPrimaryKey(pid);
    }

    public void createNewProduct(Product pro) {
        productMapper.insert(pro);
    }

    public void modifyProduct(Product pro) {
        productMapper.updateByPrimaryKey(pro);
    }

    public void dropProduct(Product pro) {
        productMapper.deleteByPrimaryKey(pro.getProId());
    }

    public List<ProductSellDetail> queryByParams(Map<String,Object> params) {
        return productMapper.queryByConditions(params);
    }

    public void createProductSellDetail(ProductSellDetail detail) {
        sellDetailMapper.insert(detail);
    }

    public List<ProductSellDetail> queryByPriority(Integer limitNum){
        return productMapper.queryByPriority(limitNum);
    }

    public List<ProductSellDetail> queryByRandom(Integer limitNum) {
        return productMapper.queryByRandom(limitNum);
    }


    public List<ProductSellDetail> queryByOrderNum(String orderNum) {
        return productMapper.queryByOrderNum(orderNum);
    }
}
