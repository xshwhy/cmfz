package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterDao chapterDao;

    @Override
    public void insertChapter(Chapter chapter) {
        chapterDao.insertChapter(chapter);
    }

    @Override
    public List<Chapter> selectChapter() {
        return chapterDao.selectAll();
    }
}
