package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_menu")
public class Menu implements Serializable {
private Integer id;
private String title;
@Column(name = "icon_cls")
private String iconCls;
@Column(name = "partent_id")
private Integer partentId;
@Column(name = "jsp_url")
private String jspUrl;
@Transient
private List<Menu> mlist;


}
