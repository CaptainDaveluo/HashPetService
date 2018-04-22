package com.hashpet.pojo;

public class URLImage {
    private String urlId;

    private String urlStr;

    private Integer urlType;

    private Integer urlTargetid;

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId == null ? null : urlId.trim();
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr == null ? null : urlStr.trim();
    }

    public Integer getUrlType() {
        return urlType;
    }

    public void setUrlType(Integer urlType) {
        this.urlType = urlType;
    }

    public Integer getUrlTargetid() {
        return urlTargetid;
    }

    public void setUrlTargetid(Integer urlTargetid) {
        this.urlTargetid = urlTargetid;
    }
}