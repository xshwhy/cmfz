package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_album")
@ExcelTarget(value = "album")
public class Album implements Serializable {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = "专辑",needMerge = true)
    private String title;
    @ExcelIgnore
    private String amount;
    @Column(name = "img_path")
    private String imgPath;
    @ExcelIgnore
    private String score;
    @ExcelIgnore
    private String author;
    @ExcelIgnore
    private String boardcast;  //播音人
    @ExcelIgnore
    @Column(name = "publish_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    @ExcelIgnore
    private String brief;  //简介
    @Transient
    @ExcelCollection(name = "章节")
    private List<Chapter> children;


}
