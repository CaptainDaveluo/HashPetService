package com.hashpet.controller;

import com.hashpet.pojo.Facilitator;
import com.hashpet.service.IFacilitatorService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FacilitatorController {

    @Autowired
    @Qualifier("facService")
    private IFacilitatorService facService;

    private static Logger logger= Logger.getLogger(FacilitatorController.class);


    @RequestMapping("showAllStores")
    public String showAllStores(HttpServletRequest request){
        JSONObject json = new JSONObject();
        try{
            List<Facilitator> facilitatorList=facService.getAllFacilitators();
            json.put("status","OK");
            json.put("message","查询商铺成功");
            JSONArray facInfo = JSONArray.fromObject(facilitatorList);
            json.put("result",facInfo);
        }catch (Exception e){
            json.put("status","OK");
            json.put("message","查询商铺失败");
            json.put("result","");
            logger.error("查询店铺过程出现异常");
        }
        request.setAttribute("result",json);
        return "showResult";
    }
}
