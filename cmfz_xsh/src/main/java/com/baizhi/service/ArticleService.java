package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Article> selectAllArticle();

    void insertArticle(Article article);

    void deleteArticle(int id);

    void updateArticle(Article article);

    List<Article>selectMyMaster(int aid);

    List<Article>selectOtherMaster(int aid);
}
