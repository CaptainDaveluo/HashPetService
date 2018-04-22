package com.hashpet.controller;

import com.hashpet.pojo.SellerInfo;
import com.hashpet.service.implement.SellerService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SellerController {
    private static Logger logger= Logger.getLogger(SellerController.class);

    @Autowired
    @Qualifier("sellerService")
    private SellerService sellerService;

    @RequestMapping("queryStoreName")
    public String queryStoreName(HttpServletRequest request){
        String storeId = request.getParameter("storeId");
        Integer store =Integer.valueOf(storeId);
        JSONObject json = new JSONObject();
        try{
            SellerInfo seller = sellerService.querySellerById(store);
            json.put("status","OK");
            json.put("message","查询商铺名成功");
            json.put("result",seller.getShopName());
        }catch (Exception e){
            e.printStackTrace();
            json.put("status","FAIL");
            json.put("message","查询失败");
            logger.error("查询商店名过程出现异常:"+e.getMessage());
            json.put("result","NULL");
        }
        request.setAttribute("result",json);
        return "showResult";
    }

}
