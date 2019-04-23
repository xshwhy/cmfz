package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

public interface BannerService {
    Map queryByStatus(int page,int rows);
    void queryInsert(Banner banner);

    void queryUpdate(Banner banner);

    void queryDelete(int id);
}
