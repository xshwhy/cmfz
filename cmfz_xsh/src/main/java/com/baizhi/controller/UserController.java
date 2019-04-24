package com.baizhi.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson.JSON;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("queryAll")
    @ResponseBody
    public Map queryAll(int page, int rows){
        return userService.queryAll(page,rows);
    }

    @RequestMapping("insertUser")
    @ResponseBody
    public Map insertUser(User user, MultipartFile file, HttpSession session){
            //获取文件上传路径
        String realPath = session.getServletContext().getRealPath("/");
        String dir=realPath+"user";
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
        user.setHeadImg(newName);
        Map map=new HashMap();
        try {
            userService.insertUser(user);
            map.put("isInsert",true);
            Map map1 = userService.selectByDate();
            String s = JSON.toJSONString(map1);

            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-1f3b41fa7a3647429e5657d9cfb2fd2b");

            goEasy.publish("xsh",s);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("isInsert",false);
        }
        return map;
    }


    @RequestMapping("deleteUser")
    @ResponseBody
    public Map deleteUser(int id){
        userService.deleteUser(id);
        Map map =new HashMap();
        return map;
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public Map updateUser(User user){
        userService.updateUser(user);
        Map map = new HashMap();
        return map;
    }



    @RequestMapping("selectBySex")
    @ResponseBody
    public Map selectBySex()
    {
    return userService.selectBySex();
    }


    @RequestMapping("selectByDate")
    @ResponseBody
    public Map selectByDate(){
        return userService.selectByDate();

    }

    @RequestMapping("login")
    @ResponseBody
    public Map login(String name,String password, String code1,HttpSession session){
        Map map=new HashMap();
        if(!session.getAttribute("code").equals(code1)){
            map.put("isLogin",1); //验证码错误
        }
        User user1 = userService.login(name, password);

        if(user1==null){
            map.put("isLogin",2);
        }
        session.setAttribute("name",name);
        return map;
    }

    @RequestMapping("captchaCode")
    public void out(HttpSession session, HttpServletResponse resp) throws IOException {
        //生成验证码
        LineCaptcha lineCaptcha= CaptchaUtil.createLineCaptcha(90,20,4,10);

        //将验证按存到session中
        session.setAttribute("code",lineCaptcha.getCode());

        //将验证码的图片传递到浏览器中
        OutputStream os=resp.getOutputStream();

        lineCaptcha.write(os);

    }








}
