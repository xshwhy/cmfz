package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @RequestMapping("selectAllArticle")
    public List<Article>selectAllArticle(){
       return articleService.selectAllArticle();
    }


    @RequestMapping("insertArticle")
    public Map insertUser(Article article, MultipartFile file, HttpSession session){
        //获取文件上传路径
        String realPath = session.getServletContext().getRealPath("/");
        String dir=realPath+"article";
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

        Date date = new Date();
        article.setImgPath(newName);
        article.setPublishDate(date);

        Map map=new HashMap();
        try {
            articleService.insertArticle(article);
            map.put("isInsert",true);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("isInsert",false);
        }
        return map;
    }

    @RequestMapping("deleteArticle")
    public Map deleteArticle(int id) {
        articleService.deleteArticle(id);
        Map map = new HashMap();
        return map;
    }

    @RequestMapping("updateArticle")
    public Map updateArticle(Article article) {
        Map map = new HashMap();
        articleService.updateArticle(article);
        return map;
    }



}
