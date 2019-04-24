package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDao albumDao;


    @Override
    public List<Album> selectAllChapter() {
        return albumDao.selectAllChapter();
    }

    @Override
    public void queryInsert(Album album) {
        albumDao.queryInsert(album);
    }

    @Override
    public List<Album> queryAll() {
        return albumDao.selectAll();
    }

    @Override
    public Album selectOne(int id) {
        return albumDao.selectByPrimaryKey(id);
    }
}
