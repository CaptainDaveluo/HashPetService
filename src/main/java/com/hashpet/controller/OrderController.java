package com.hashpet.controller;

import com.hashpet.pojo.GoodsInfo;
import com.hashpet.pojo.Order;
import com.hashpet.pojo.OrderItem;
import com.hashpet.pojo.Product;
import com.hashpet.service.IOrderService;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderController {
    private static Logger logger= Logger.getLogger(OrderController.class);

    @Autowired
    @Qualifier("orderService")
    private IOrderService orderService;

    @Autowired
    @Qualifier("productService")
    private IProductService productService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @RequestMapping("/newOrder")
    public String newOrder(HttpServletRequest request,Model model){
        String ordType = request.getParameter("ordType");
        String ordAddr = request.getParameter("ordAddr");
        String ordUserId = request.getParameter("ordUserId");
        String ordPhone = request.getParameter("ordPhone");
        String details = request.getParameter("details");
        Double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        JSONObject json = new JSONObject();
        try{
            //解析传入的订单详情
            JSONObject jsonObject = JSONObject.fromObject(details);
            Iterator<Map.Entry<String,JSONArray>> iterator=  jsonObject.entrySet().iterator();
            while(iterator.hasNext()){
                //提交的数据可能有多家店铺，按照每个商家建立订单
                Map.Entry<String,JSONArray> entry=iterator.next();
                JSONArray orderDetailList = entry.getValue();
                Order order = new Order();
                order.setOrdShopId(Integer.parseInt(entry.getKey()));
                order.setOrdAddr(ordAddr);
                order.setOrdType(Integer.valueOf(ordType));
                order.setOrdUserId(Integer.valueOf(ordUserId));
                order.setOrdPhone(ordPhone);
                Date date = new Date();
                order.setOrdTime(date);
                String orderNum = "G"+ordUserId+date.getTime();
                order.setOrdNum(orderNum);
                order.setOrdStatus(0);
                order.setOrdPrice(totalPrice);
                orderService.newOrder(order);
                List<OrderItem> items =new ArrayList<OrderItem>();
                //解析订单详情
                for(int i=0;i<orderDetailList.size();i++){
                    Object obj = orderDetailList.get(i);
                    GoodsInfo orderDetail = (GoodsInfo) JSONObject.toBean(JSONObject.fromObject(obj),GoodsInfo.class);
                    OrderItem item = new OrderItem();
                    item.setOrdProId(Integer.parseInt(orderDetail.getId()));
                    item.setOrdNum(orderDetail.getCount());
                    item.setOrdSingleP(BigDecimal.valueOf(orderDetail.getPrice()));
                    item.setOrdId(order.getOrdId());
                    items.add(item);
                }
                orderService.insertOrderItem(items);
                json.put("status","OK");
                json.put("result",jsonObject);
                json.put("message","订单提交成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            json.put("status","FAILED");
            json.put("result","NULL");
            json.put("message","订单提交过程出现异常");
            logger.error(e.getMessage());
        }
        request.setAttribute("result",json);
        return "showResult";
    }

    @RequestMapping("/showUserOrder")
    public String showUserOrder(HttpServletRequest request){
        String strUserId = request.getParameter("userId");
        Integer userId = Integer.parseInt(strUserId);
        try {
            List<Order> orders = orderService.queryOrderByUserId(userId);
            List<OrderItem> details = orderService.queryOrderDetailByUserId(userId);
            JSONObject json = new JSONObject();
            JSONArray datas=generateOrder(orders,details);
            json.put("status", "OK");
            json.put("result", datas);
            json.put("message", "订单信息列表");
            request.setAttribute("result", json);
        }catch (Exception e){
            logger.error("查询用户订单过程出错:"+e.getMessage());
        }
        return "showResult";
    }


    @RequestMapping("/showSellerOreder")
    public String showSellerOreder(HttpServletRequest request){
        String strSellerId = request.getParameter("sellerId");
        Integer sellerId = Integer.parseInt(strSellerId);
        try {
            List<Order> orders = orderService.queryOrderBySellerId(sellerId);
            List<OrderItem> details = orderService.queryOrderDetailBySellerId(sellerId);
            JSONObject json = new JSONObject();
            JSONArray datas=generateOrder(orders,details);
            json.put("status", "OK");
            json.put("result", datas);
            json.put("message", "订单信息列表");
            request.setAttribute("result", json);
        }catch (Exception e){
            logger.error("查询订单过程出错:"+e.getMessage());
        }
        return "showResult";
    }

    private JSONArray generateOrder(List<Order> orders,List<OrderItem> details){
        JSONArray datas = new JSONArray();
        for (int i = 0; i < orders.size(); i++) {
            JSONObject orderData = new JSONObject();
            Order order = orders.get(i);
            JSONArray arrayOrder;
            List<OrderItem> items = new ArrayList<OrderItem>();
            for (int j = 0; j < details.size(); j++) {
                OrderItem item = details.get(j);
                if (order.getOrdId() == item.getOrdId()) {
                    items.add(item);
                }
            }
            Product p = productService.queryProductById(details.get(0).getOrdProId());
            arrayOrder = JSONArray.fromObject(items);
            orderData.put("ordId", order.getOrdId());
            orderData.put("ordNum", order.getOrdNum());
            orderData.put("ordPhone", order.getOrdPhone());
            orderData.put("ordStatus", order.getOrdStatus());
            orderData.put("ordUserId", order.getOrdUserId());
            orderData.put("ordAddr", order.getOrdAddr());
            orderData.put("ordType", order.getOrdType());
            orderData.put("ordPrice", order.getOrdPrice());
            orderData.put("ordShopId", order.getOrdShopId());
            orderData.put("ordTime", sdf.format(order.getOrdTime()));
            orderData.put("ordGoodsName",p.getProName());
            orderData.put("items", arrayOrder);
            datas.add(orderData);
        }
        return datas;
    }


    @RequestMapping("/sendOffOrder")
    public String sendOffOrder(HttpServletRequest request){
        String orderNum = request.getParameter("orderNum");
        Order order = new Order();
        order.setOrdNum(orderNum);
        order.setOrdStatus(1);
        doUpdateOderStatus(order,request);
        return "showResult";
    }

    @RequestMapping("/comfirmOrder")
    public String comfirmOrder(HttpServletRequest request){
        String orderNum = request.getParameter("orderNum");
        Order order = new Order();
        order.setOrdNum(orderNum);
        order.setOrdStatus(2);
        doUpdateOderStatus(order,request);
        return "showResult";
    }

    public void doUpdateOderStatus(Order order,HttpServletRequest request){
        JSONObject json = new JSONObject();
        try {
            orderService.updateOrderByNum(order);
            json.put("status", "OK");
            json.put("result", "发货成功");
            json.put("message", "订单发货");
            request.setAttribute("result", json);
        }catch (Exception e){
            json.put("status", "FAIL");
            json.put("result", "发货失败");
            json.put("message", "订单发货");
            request.setAttribute("result", json);
        }
    }
}
