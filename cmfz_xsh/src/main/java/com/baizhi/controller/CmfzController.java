package com.baizhi.controller;

import com.baizhi.entity.Error;
import com.baizhi.entity.User;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("first")
public class CmfzController {

    @Autowired
    BannerService bannerService;

    @Autowired
    ArticleService articleService;

    @Autowired
    AlbumService albumService;

    @Autowired
    UserService userService;


    @RequestMapping("queryAll")
    public Object queryAll(Integer uid,String type,String sub_type){
        if (uid==null||type==null){
            return new Error("参数不能为空");
        }else {
            if (type.equals("all")){
                Map<String,Object> map = new HashMap<>();
                map.put("banner",bannerService.selectAllB());
                map.put("album",albumService.queryAll());
                map.put("article",articleService.selectAllArticle());
                return map;
            }else if (type.equals("wen")){
                Map<String,Object> map = new HashMap<>();
                map.put("album",albumService.queryAll());
                return map;
            }else {
                if (sub_type==null){
                    return new Error("思类型为空未查到数据");
                }else {
                    if (sub_type.equals("ssyj")){
                        User user = userService.selectOne(uid);
                        Integer aid = user.getMasterId();
                        Map<String,Object> map = new HashMap<>();
                        map.put("article",articleService.selectMyMaster(aid));
                        return map;
                    }else {
                        User user = userService.selectOne(uid);
                        Integer aid = user.getMasterId();
                        Map<String,Object> map = new HashMap<>();
                        map.put("article",articleService.selectOtherMaster(aid));
                        return map;
                    }
                }
            }
        }
    }

    @RequestMapping("wen")
    public Object queryWen(Integer id,Integer uid) {
        if (uid == null) {
            return new Error("需要登录");
        }else if(id==null){
            return new Error("需要选择");
        }
        else {
            Map<String, Object> map = new HashMap<>();
            map.put("album", albumService.selectOne(id));
            return map;
        }
    }

    @RequestMapping("login")
    public Object login(String phone, String password, String name) {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userService.selectAllUser();
        for (User user : users) {
            String password1 = user.getPassword();
            if (password != password1) {
                return new Error("密码错误");
            } else {
                map.put("user", userService.login(name, password));
            }
        }
        return map;

    }

    @RequestMapping("regist")
    public Object regist(String phone,String password,String name ) {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userService.selectAllUser();
        for (User user : users) {
            String phone1 = user.getPhone();
            if(phone==phone1){
                return new Error("手机号已存在");
            }else {
                map.put("user",userService.login(name,password));
            }
        }
        return map;
    }



}
