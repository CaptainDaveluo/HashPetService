package com.hashpet.controller;

import com.hashpet.pojo.SellerInfo;
import com.hashpet.pojo.User;
import com.hashpet.service.ISellerService;
import com.hashpet.service.IUserService;
import com.hashpet.utils.MD5Util;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {


    private static Logger logger= Logger.getLogger(UserController.class);

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    @Autowired
    @Qualifier("sellerService")
    private ISellerService sellerService;

    public ISellerService getSellerService() {
        return sellerService;
    }

    public void setSellerService(ISellerService sellerService) {
        this.sellerService = sellerService;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        UserController.logger = logger;
    }

    /**
     * 用户注册
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/doUserRegist")
    public String userRegist(HttpServletRequest request, Model model){
        //String userName = request.getParameter("userName");
        String userPass = MD5Util.md5( request.getParameter("userPass"));
        String userType = request.getParameter("userType");
        String phoneNum = request.getParameter("phoneNum");
        JSONObject object = new JSONObject();


        String nickName = "USER_"+phoneNum;     //默认昵称
        User user = new User();
        user.setPhonenum(phoneNum);
        user.setPassword(userPass);
        user.setUsertype(userType);
        user.setGender("GG");                   //默认性别
        user.setNickname(nickName);
        user.setIntergral(0);

        try {
            //验证用户是否已经被注册
            if(userService.isUserExist(phoneNum)>0){
                object.put("status","FAIL");
                object.put("message","手机号码已被注册");
            }else {
                userService.newUser(user);
                object.put("status", "OK");
                object.put("message", "注册成功");
                JSONObject userInfo = JSONObject.fromObject(user);
                object.put("result",userInfo);
            }
            request.setAttribute("result", object);
        }catch (Exception e){
            logger.error("json create error:"+e.getMessage());
        }
        return "showResult";
    }

    /**
     * 登陆操作
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/doUserLogin")
    public String doLogin(HttpServletRequest request, Model model){
        String phoneNum = request.getParameter("phoneNum");
        String userPass = MD5Util.md5(request.getParameter("userPass"));
        User user = new User();
        user.setPhonenum(phoneNum);
        user.setPassword(userPass);
        try {
            User result = userService.chechUser(user);
            JSONObject json = new JSONObject();
            if (result == null) {
                json.put("status", "FAIL");
                json.put("message","用户不存在或者密码错误");
            }else{
                json.put("status","OK");
                JSONObject userInfo = JSONObject.fromObject(result);
                json.put("result",userInfo);
                json.put("message","用户登录成功");
            }
            request.setAttribute("result",json);
        }catch (Exception e){
            logger.error("用户登陆时出现异常"+e.getMessage());
        }
        return "showResult";
    }

    @RequestMapping("/registSeller")
    public String registSeller(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String shopName = request.getParameter("shopName");
        String shopAddress = request.getParameter("address");
        String shopBrief = request.getParameter("brief");
        String phoneNum = request.getParameter("phoneNum");
        User user = userService.getUserById(Integer.parseInt(userId));

        SellerInfo seller = new SellerInfo();
        seller.setAddress(shopAddress);
        seller.setBelongUserId(user.getUserid());
        seller.setPhoneNum(phoneNum);
        seller.setShopBrief(shopBrief);
        seller.setShopName(shopName);
        JSONObject json = new JSONObject();
        try {
            sellerService.newSeller(seller);
            userService.RegistSaller(user);

            json.put("status","OK");
            json.put("message","创建商铺成功");
            JSONObject result = JSONObject.fromObject(seller);
            json.put("result",result);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("创建商铺失败"+e.getMessage());
            json.put("status","FAIL");
            json.put("message","创建商品失败");
            json.put("result","NULL");
        }
        request.setAttribute("result",json);
        return "showResult";
    }

    /**
     * 通过用户id获取商家信息
     * @param request
     * @return
     */
    @RequestMapping("getSellerInfo")
    public String getSellerInfo(HttpServletRequest request){
        String userId = request.getParameter("userId");
        User user =new User();
        user.setUserid(Integer.parseInt(userId));
        JSONObject json = new JSONObject();
        try{
            SellerInfo seller=sellerService.getSellerInfoByUser(user);
            json.put("status","OK");
            json.put("message","获取店铺信息成功");
            JSONObject result = JSONObject.fromObject(seller);
            json.put("result",result);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取店铺信息失败"+e.getMessage());
            json.put("status","FAIL");
            json.put("message","获取店铺信息失败");
            json.put("result","NULL");
        }
        request.setAttribute("result",json);
        return "showResult";
    }
}
