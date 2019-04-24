package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map queryAll(int page, int rows);

    void insertUser(User user);

    void deleteUser(int id);

    void updateUser(User user);


    Map selectBySex();

    Map selectByDate();

    User login(String name,String password);

    User selectOne(int id);

    List<User>selectAllUser();


}
