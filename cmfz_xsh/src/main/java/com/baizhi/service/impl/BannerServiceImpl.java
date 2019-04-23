package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bannerService")
@Transactional
public class BannerServiceImpl implements BannerService {

   @Autowired
    BannerDao bannerDao;

    @Override
    public Map queryByStatus(int page,int rows) {
        Map map = new HashMap();
        int b=page*rows-rows;
        RowBounds rowBounds = new RowBounds(b,rows);
        List<Banner> banners = bannerDao.selectByRowBounds(new Banner(), rowBounds);

        map.put("rows",banners);
        map.put("total",bannerDao.selectAll().size());
        return map;
    }

    @Override
    public void queryInsert(Banner banner) {
       bannerDao.queryInsert(banner);
    }

    @Override
    public void queryUpdate(Banner banner) {
         bannerDao.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void queryDelete(int id) {
        bannerDao.deleteByPrimaryKey(id);
    }
}
