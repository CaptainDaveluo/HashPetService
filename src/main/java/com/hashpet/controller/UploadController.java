package com.hashpet.controller;

import com.hashpet.pojo.URLImage;
import com.hashpet.service.IURLImageService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;


@Controller()
public class UploadController {

    @Autowired
    @Qualifier("urlService")
    private IURLImageService urlService;

    private static Logger logger = Logger.getLogger(UploadController.class);

    /**
     * 上传商品图片文件s
     * @param request
     * @param file
     * @return
     */
    @RequestMapping("uploadProPic")
    public String uploadProPic(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file){
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
        String newName="upload/"+new Date().getTime()+suffix;
        String path = request.getSession().getServletContext().getRealPath("/")+newName;
        String root = request.getSession().getServletContext().getRealPath("/")+"upload/";
        if(!new File(root).exists()){
            new File(root).mkdir();
        }
        File picFile = new File(path);
        JSONObject json = new JSONObject();
        try {
            file.transferTo(picFile);

            URLImage image = new URLImage();
            image.setUrlId(String.valueOf(new Date().getTime()));
            image.setUrlStr(newName);
            //设置定位类型为商品图片
            image.setUrlType(1);
            //更新到持久层

            //输出到json
            try {
                urlService.newImage(image);
                json.put("status","OK");
                JSONObject imageInfo = JSONObject.fromObject(image);
                json.put("result",imageInfo);
                json.put("message","图片上传成功");
            }catch (Exception e){
                e.printStackTrace();
                logger.error("图片上传出现异常"+e.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("图片上传出现异常"+e.getMessage());
        }
        request.setAttribute("result",json);
        return "showResult";
    }

}
