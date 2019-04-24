package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {

    void insertUser(User user);

    User selectByName(@Param("name")String name,@Param("password")String password);




}
