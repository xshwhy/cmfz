package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {

    List<Album>selectAllChapter();

    void queryInsert(Album album);

    List<Album>queryAll();

    Album selectOne(int id);
}
