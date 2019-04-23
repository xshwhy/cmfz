package com.baizhi.dao;

import com.baizhi.entity.UserCount;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserCountDao extends Mapper<UserCount> {

    List<UserCount> selectByMale();

    List<UserCount>selectByFeMale();

    int selectByDate(int time);
}
