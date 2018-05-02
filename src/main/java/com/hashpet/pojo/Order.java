package com.hashpet.pojo;

import java.util.Date;

/**
 * 订单表实体类
 */
public class Order {
    private Integer ordId;         //订单id
    private String  ordNum;        //订单编号
    private String  ordAddr;       //订单地址
    private Double  ordPrice;      //订单总价
    private Integer ordShopId;    //商家id
    private Integer ordUserId;    //客户id
    private Integer ordType;       //订单类型,1为商品订单，2为线下服务订单
    private Date    ordTime;       //订单日期
    private Integer ordStatus;     //订单状态0为未完成，1为已付款,2为已完成
    private String  ordPhone;      //联系方式
    private String  ordGoodsName;  //首件商品(服务)信息

    public Integer getOrdId() {
        return ordId;
    }

    public void setOrdId(Integer ordId) {
        this.ordId = ordId;
    }

    public String getOrdNum() {
        return ordNum;
    }

    public void setOrdNum(String ordNum) {
        this.ordNum = ordNum;
    }

    public String getOrdAddr() {
        return ordAddr;
    }

    public void setOrdAddr(String ordAddr) {
        this.ordAddr = ordAddr;
    }

    public Double getOrdPrice() {
        return ordPrice;
    }

    public void setOrdPrice(Double ordPrice) {
        this.ordPrice = ordPrice;
    }

    public Integer getOrdShopId() {
        return ordShopId;
    }

    public void setOrdShopId(Integer ordShopId) {
        this.ordShopId = ordShopId;
    }

    public Integer getOrdUserId() {
        return ordUserId;
    }

    public void setOrdUserId(Integer ordUserId) {
        this.ordUserId = ordUserId;
    }

    public Integer getOrdType() {
        return ordType;
    }

    public void setOrdType(Integer ordType) {
        this.ordType = ordType;
    }

    public Date getOrdTime() {
        return ordTime;
    }

    public void setOrdTime(Date ordTime) {
        this.ordTime = ordTime;
    }

    public Integer getOrdStatus() {
        return ordStatus;
    }

    public void setOrdStatus(Integer ordStatus) {
        this.ordStatus = ordStatus;
    }

    public String getOrdPhone() {
        return ordPhone;
    }

    public void setOrdPhone(String ordPhone) {
        this.ordPhone = ordPhone;
    }

    public String getOrdGoodsName() {
        return ordGoodsName;
    }

    public void setOrdGoodsName(String ordGoodsName) {
        this.ordGoodsName = ordGoodsName;
    }
}
