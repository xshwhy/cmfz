package com.baizhi;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Album;
import com.baizhi.service.BannerService;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzXshApplicationTests {

    @Autowired
    MenuDao menuDao;

    @Autowired
    BannerService bannerService;

    @Autowired
    AlbumDao albumDao;


    @Test
    public void contextLoads() {
        List<Album> albums = albumDao.selectAllChapter();
        System.out.println(albums);
    }

    @Test
    public void test1() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);

    }

    @Test
    public void test22() {
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-1f3b41fa7a3647429e5657d9cfb2fd2b");

                goEasy.publish("xsh","Hello, GoEasy!");


    }

    }


