package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import com.baizhi.util.AudioUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @Autowired
    AlbumService albumService;

    @RequestMapping("insertChapter")
    public void insertChapter(Chapter chapter, MultipartFile audio, HttpSession session)  {


        //文件的大小
        String printSize = getPrintSize(audio.getSize());

        //获取文件上传的目录
        String realPath = session.getServletContext().getRealPath("/");
        String dir=realPath+"file2";
        File file = new File(dir);
        if(!file.exists()){
            file.mkdir();
        }


        //重命名  测试.mp3
        String originalFilename = audio.getOriginalFilename();
        //mp3
        String extension = FilenameUtils.getExtension(originalFilename);
        String newName = UUID.randomUUID().toString();
        //s7de98r9u8wy23hgf.mp3
        newName = newName+"."+extension;


        //上传
        File file1 = null;
        try {
            file1 = new File(dir, newName);
            audio.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取时长
        Long duration = AudioUtil.getDuration(file1);
        String s = duration.toString();
        chapter.setSize(printSize);
        chapter.setDuration(s);
        chapter.setMusicPath(newName);

        chapterService.insertChapter(chapter);
        System.out.println(chapter);


    }


    @RequestMapping("download")
    public void downLoad(String title, String musicPath, HttpSession session, HttpServletResponse response){
        System.out.println(1111111);
        //获取文件的路径
        String realPath = session.getServletContext().getRealPath("/file2");
        String filePath = realPath + "/" + musicPath;
        File file = new File(filePath);
        //修改下载时的名字
        String extension = FilenameUtils.getExtension(musicPath);
        String oldName = title+"."+extension;
        //下载
        //设置响应的编码
        String encode =null;
        try {
            encode= URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置响应头
        response.setHeader("Content-Disposition","attachment;fileName="+encode);
        //设置响应类型
        response.setContentType("audio/mpeg");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("dao")
    public Map dao(){
        List<Chapter> chapters = chapterService.selectChapter();
        List<Album> albums = albumService.queryAll();
        for (Album album : albums) {
            album.setChildren(chapters);
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑","章节"),
                Album.class, albums);
        try {
            workbook.write(new FileOutputStream(new File("D:/easy.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map map=new HashMap();
        return map;

    }



























    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

  



}
