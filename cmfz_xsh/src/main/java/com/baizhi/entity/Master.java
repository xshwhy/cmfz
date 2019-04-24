package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cmfz_master")
public class Master {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String dharma;
    private Integer status;
    @Column(name = "head_img")
    private String headImg;

}
