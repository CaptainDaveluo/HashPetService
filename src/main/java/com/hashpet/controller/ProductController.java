package com.hashpet.controller;

import com.hashpet.pojo.PetInfo;
import com.hashpet.pojo.Product;
import com.hashpet.pojo.ProductSellDetail;
import com.hashpet.service.IPetService;
import com.hashpet.service.IProductService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    private static Logger logger= Logger.getLogger(ProductController.class);

    @Autowired
    @Qualifier("productService")
    private IProductService productService;

    @Autowired
    @Qualifier("petService")
    private IPetService     petService;

    /**
     * 以指定方式查询商品
     * @return
     */
    @RequestMapping("/queryProduct")
    public String queryProduct(HttpServletRequest request,Model model){
        //查询参数
        String proName = request.getParameter("proName");
        String proTag = request.getParameter("proTag");
        String proPrice = request.getParameter("proPrice");
        String priceCompare = request.getParameter("priceCompare");
        String proShopId = request.getParameter("proShopId");
        Map conditions = new HashMap();
        conditions.put("proName",proName);
        conditions.put("proTag",proTag);
        conditions.put("proPrice",proPrice);
        conditions.put("priceCompare",priceCompare);
        conditions.put("proShopId",proShopId);
        JSONObject json = new JSONObject();
        try{
            List<ProductSellDetail> results=productService.queryByParams(conditions);
            json.put("status","OK");
            json.put("message","查询商品成功");
            JSONArray proInfo = JSONArray.fromObject(results);
            json.put("result",proInfo);
        }catch (Exception e){
            json.put("status","FAIL");
            json.put("message","查询商品失败");
            logger.error("查询商品过程出现异常:"+e.getMessage());
            json.put("result","NULL");
        }
        request.setAttribute("result",json);
        return "showResult";
    }

    @RequestMapping("/newProduct")
    public String newProduct(HttpServletRequest request,Model model){
        String proName = request.getParameter("proName");
        String proTag   = request.getParameter("proTag");
        double price    = Double.valueOf(request.getParameter("proPrice"));
        Integer proIntergral = Integer.parseInt(request.getParameter("proIntergral"));
        String urlId=request.getParameter("urlId");
        String proBrief = request.getParameter("brief");
        Integer proShopId = Integer.parseInt(request.getParameter("shopId"));
        Integer proStock = Integer.parseInt(request.getParameter("proStock"));
        Product pro = new Product();
        pro.setProName(proName);
        pro.setProBrief(proBrief);
        pro.setProIntergral(proIntergral);
        pro.setProPrice(price);
        pro.setProSrc(urlId);
        pro.setProShopId(proShopId);
        pro.setProTag(proTag);
        //新建库存及销量信息
        ProductSellDetail detail = new ProductSellDetail();
        JSONObject json = new JSONObject();
        try{
            productService.createNewProduct(pro);
            detail.setProId(pro.getProId());
            detail.setProStock(proStock);
            productService.createProductSellDetail(detail);
            json.put("status","OK");
            json.put("message","创建商品成功");
            JSONObject proInfo = JSONObject.fromObject(pro);
            json.put("result",proInfo);
        }catch (Exception e){
            json.put("status","FAIL");
            json.put("message","创建商品失败");
            logger.error("创建商品过程出现异常:"+e.getMessage());
            json.put("result","NULL");
        }
        request.setAttribute("result",json);
        return "showResult";
    }

    @RequestMapping("/createNewPet")
    public String createNewPet(HttpServletRequest request){
        String urlId=request.getParameter("urlId");
        String gender = request.getParameter("gender");
        String petName = request.getParameter("petName");
        String petType = request.getParameter("petType");
        Integer petAge = Integer.parseInt(request.getParameter("petAge"));
        Double price = Double.valueOf(request.getParameter("price"));
        String brief = request.getParameter("brief");
        int shopId = Integer.parseInt(request.getParameter("shopId"));
        Integer proStock = Integer.parseInt(request.getParameter("proStock"));
        //宠物作为特殊商品，需要同时加入商品表和宠物信息表
        Product product = new Product();
        PetInfo petInfo = new PetInfo();
        product.setProName(petName);
        product.setProPrice(price);
        product.setProTag(petType);
        product.setProBrief(brief);
        product.setProShopId(shopId);
        product.setProSrc(urlId);
        product.setProIntergral(100);
        //宠物信息填充
        petInfo.setPetName(petName);
        petInfo.setPetAge(petAge);
        petInfo.setPetClass(petType);
        petInfo.setPetGender(gender);
        petInfo.setPetHotLevel(0);
        JSONObject json = new JSONObject();
        try{
            productService.createNewProduct(product);
            petService.createNewPet(petInfo);
            //新建库存及销量信息
            ProductSellDetail detail = new ProductSellDetail();
            detail.setProId(product.getProId());
            detail.setProStock(proStock);
            productService.createProductSellDetail(detail);
            json.put("status","OK");
            json.put("message","新宠上架成功");
            JSONObject result = JSONObject.fromObject(product);
            json.put("result",result);
        }catch (Exception e){
            json.put("status","FAIL");
            json.put("message","新宠商家失败");
            json.put("result","NULL");
            logger.error("新建宠物过程失败"+e.getMessage());
        }
        request.setAttribute("result",json);
        return "showResult";
    }


    /**
     * 获取首页商品数据
     * @param request
     * @return
     */
    @RequestMapping("/getIndexProduct")
    public String getIndexProduct(HttpServletRequest request){
        //获取的商品数
        Integer limitNum = Integer.parseInt(request.getParameter("limitNum"));
        JSONObject json = new JSONObject();
        try {
            List<ProductSellDetail> results = productService.queryByPriority(limitNum);
            json.put("status","OK");
            json.put("message","获取首页商品列表信息成功");
            JSONArray jsonResults = JSONArray.fromObject(results);
            json.put("result",jsonResults);
        }catch (Exception e){
            json.put("status","FAIL");
            json.put("message","获取首页商品列表信息失败");
            json.put("result","NULL");
            e.printStackTrace();
        }
        request.setAttribute("result",json);
        return "showResult";
    }


    @RequestMapping("/getOrderGoods")
    public String getOrderGoods(HttpServletRequest request){
        String orderNum =request.getParameter("orderNum");
        JSONObject json = new JSONObject();
        try {
            List<ProductSellDetail> results = productService.queryByOrderNum(orderNum);
            json.put("status","OK");
            json.put("message","获取商品列表信息成功");
            JSONArray jsonResults = JSONArray.fromObject(results);
            json.put("result",jsonResults);
        }catch (Exception e){
            json.put("status","FAIL");
            json.put("message","获取商品列表信息失败");
            json.put("result","NULL");
            logger.error("查询失败"+e.getMessage());
        }
        request.setAttribute("result",json);
        return "showResult";
    }
}
