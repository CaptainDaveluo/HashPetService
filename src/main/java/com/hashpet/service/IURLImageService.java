package com.hashpet.service;

import com.hashpet.pojo.URLImage;

public interface IURLImageService {
    public void newImage(URLImage image);
    public URLImage queryURL(String urlId);
    public URLImage queryURLByGoodsId(String goodsId);
}
