package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @RequestMapping("queryByStatus")
    public  @ResponseBody Map queryByStatusId(int page,int rows){

        return bannerService.queryByStatus(page,rows);
    }

    @RequestMapping("queryInsert")
    public @ResponseBody Map queryInsert(Banner banner, MultipartFile file) throws Exception {
        String oldName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String newName = uuid+oldName.substring(oldName.lastIndexOf("."));
        String filePath="E://url//";
        file.transferTo(new File(filePath+newName));
        banner.setImgPath(newName);
        Map map = new HashMap();
        try {
            bannerService.queryInsert(banner);
            map.put("isInsert",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isInsert",false);
        }
        return map;

    }


    @RequestMapping("queryDelete")
    @ResponseBody
    public Map queryDelete(int id){
        Map map=new HashMap();
        bannerService.queryDelete(id);
        return map;
    }

    @RequestMapping("queryUpdate")
    @ResponseBody
    public Map queryUpdate(Banner banner){
        Map map=new HashMap();
        bannerService.queryUpdate(banner);
        return map;

    }


}
