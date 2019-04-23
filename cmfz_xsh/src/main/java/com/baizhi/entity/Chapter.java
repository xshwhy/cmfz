package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_chapter")
public class Chapter implements Serializable {
@Id
@KeySql(useGeneratedKeys = true)
@Excel(name = "编号")
private Integer id;
@Excel(name = "标题")
private String title;
@Excel(name = "大小")
private String size;
@ExcelIgnore
private String duration;
@Excel(name="时间",format = "yyyy-MM-dd HH:mm:ss",width = 50)
@Column(name = "publish_date")
@JSONField(format = "yyyy-MM-dd HH:mm:ss")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private Date publishDate;
@ExcelIgnore
@Column(name = "album_id")
private String albumId;
@Column(name = "music_path")
@Excel(name="头像",type = 2,width = 40,height = 20)
private String musicPath;





}
