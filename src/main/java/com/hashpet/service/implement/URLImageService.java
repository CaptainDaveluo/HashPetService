package com.hashpet.service.implement;

import com.hashpet.dao.URLImageMapper;
import com.hashpet.pojo.URLImage;
import com.hashpet.service.IURLImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("urlService")
public class URLImageService implements IURLImageService{
    @Autowired
    private URLImageMapper urlImageMapper;


    public URLImageMapper getUrlImageMapper() {
        return urlImageMapper;
    }

    public void setUrlImageMapper(URLImageMapper urlImageMapper) {
        this.urlImageMapper = urlImageMapper;
    }

    public void newImage(URLImage image) {
        urlImageMapper.insert(image);
    }

    public URLImage queryURL(String urlId) {
        return urlImageMapper.selectByPrimaryKey(urlId);
    }

    public URLImage queryURLByGoodsId(String goodsId){
        return  urlImageMapper.selectByGoodsId(goodsId);
    }
}
