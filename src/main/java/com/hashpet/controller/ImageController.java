package com.hashpet.controller;

import com.hashpet.pojo.URLImage;
import com.hashpet.service.IURLImageService;
import com.hashpet.service.implement.URLImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
public class ImageController {
    private static Logger logger= Logger.getLogger(ImageController.class);

    @Autowired
    @Qualifier("urlService")
    private IURLImageService urlService;

    @RequestMapping("/getImage")
    public void  getImage(HttpServletRequest request, HttpServletResponse response){
        String urlId=request.getParameter("urlId");
        URLImage image = urlService.queryURL(urlId);
        getImage(image,request,response);
    }

    @RequestMapping("/getGoodsImage")
    public void getGoodsImage(HttpServletRequest request,HttpServletResponse response){
        String goodsId=request.getParameter("goodsId");
        URLImage image = urlService.queryURLByGoodsId(goodsId);
        getImage(image,request,response);
    }

    private void getImage(URLImage image,HttpServletRequest request,HttpServletResponse response){
        String path = request.getSession().getServletContext().getRealPath("/")+image.getUrlStr();
        try {
            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            OutputStream outputStream=response.getOutputStream();
            response.setContentType("imge/jpeg");
            response.addHeader("Content-Disposition", "attachment;filename=image.png");
            response.setCharacterEncoding("utf-8");
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("资源不存在"+e.getMessage());
        }
    }
}
