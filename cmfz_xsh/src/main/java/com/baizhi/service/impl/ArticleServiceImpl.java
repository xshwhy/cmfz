package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

   @Autowired
   ArticleDao articleDao;


    @Override
    public List<Article> selectAllArticle() {
        return articleDao.selectAll();

    }

    @Override
    public void insertArticle(Article article) {
        articleDao.insertSelective(article);
    }

    @Override
    public void deleteArticle(int id) {
        articleDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public List<Article> selectMyMaster(int aid) {
        return articleDao.selectMyMaster(aid);
    }

    @Override
    public List<Article> selectOtherMaster(int aid) {
        return articleDao.selectOtherMaster(aid);
    }


}
