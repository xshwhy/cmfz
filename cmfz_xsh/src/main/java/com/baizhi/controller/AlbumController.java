package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("album")
public class AlbumController {

    @Autowired
    AlbumService albumService;


    @RequestMapping("selectAllChapter")
    public @ResponseBody
    List<Album>selectAllChapter(){
        List<Album> albums = albumService.selectAllChapter();
        System.out.println(albums);
        return albums;
    }


    @RequestMapping("queryInsert")
    public @ResponseBody Map  queryInsert(Album album, MultipartFile file) throws IOException {
        String oldName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String newName = uuid+oldName.substring(oldName.lastIndexOf("."));
        String filePath="E://url//";
        file.transferTo(new File(filePath+newName));
        album.setImgPath(newName);
        Map map = new HashMap();
        try {
            albumService.queryInsert(album);
            map.put("isInsert",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isInsert",false);
        }
        return map;
    }

    @RequestMapping("queryAll")
    @ResponseBody
    public List<Album>queryAll(HttpSession session){
        List<Album> albums = albumService.queryAll();
        session.setAttribute("albums",albums);
        return albums;
    }

}

