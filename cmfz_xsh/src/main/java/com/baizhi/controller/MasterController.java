package com.baizhi.controller;


import com.baizhi.entity.Master;
import com.baizhi.service.MasterService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("master")
public class MasterController {

    @Autowired
    MasterService masterService;


    @RequestMapping("selectAllMaster")
    public List<Master> selectAllMaster(){

        return masterService.SelectAllMaster();
    }


    @RequestMapping("insertMaster")
    public Map insertMaster(Master master, MultipartFile file, HttpSession session){


        //获取文件上传路径
        String realPath = session.getServletContext().getRealPath("/");
        String dir=realPath+"master";
        File file1=new File(dir);
        if(!file1.exists()){
            file1.mkdir();
        }
        //获取文件原来的名字
        String originalFilename = file.getOriginalFilename();
        //取得文件的后缀
        String extension = FilenameUtils.getExtension(originalFilename);
        //获取新名的前边的部分
        String newName = UUID.randomUUID().toString();
        //拼接
        newName=newName+"."+extension;

        //上传
        File file2 = null;
        try {
            file2=new File(dir,newName);
            file.transferTo(file2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        master.setHeadImg(newName);
        Map map=new HashMap();
        try {

           masterService.insertMaster(master);
            map.put("isInsert",true);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("isInsert",false);
        }
        return map;
    }

    @RequestMapping("deleteMaster")
    public Map deleteMaster(int id) {
        masterService.deleteMaster(id);
        Map map = new HashMap();
        return map;
    }

    @RequestMapping("updateMaster")
    public Map updateMaster(Master master) {
        Map map = new HashMap();
        masterService.updateMaster(master);
        return map;
    }




}
