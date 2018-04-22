package com.hashpet.pojo;

public class ProductSellDetail extends Product{
    private Integer proId;

    private Integer proStock;

    private Integer proVisited;

    private Integer proMonthSelled;

    private Integer proSeasonSelled;

    private Integer proTotalSelled;

    private Integer proPriority;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getProStock() {
        return proStock;
    }

    public void setProStock(Integer proStock) {
        this.proStock = proStock;
    }

    public Integer getProVisited() {
        return proVisited;
    }

    public void setProVisited(Integer proVisited) {
        this.proVisited = proVisited;
    }

    public Integer getProMonthSelled() {
        return proMonthSelled;
    }

    public void setProMonthSelled(Integer proMonthSelled) {
        this.proMonthSelled = proMonthSelled;
    }

    public Integer getProSeasonSelled() {
        return proSeasonSelled;
    }

    public void setProSeasonSelled(Integer proSeasonSelled) {
        this.proSeasonSelled = proSeasonSelled;
    }

    public Integer getProTotalSelled() {
        return proTotalSelled;
    }

    public void setProTotalSelled(Integer proTotalSelled) {
        this.proTotalSelled = proTotalSelled;
    }

    public Integer getProPriority() {
        return proPriority;
    }

    public void setProPriority(Integer proPriority) {
        this.proPriority = proPriority;
    }
}