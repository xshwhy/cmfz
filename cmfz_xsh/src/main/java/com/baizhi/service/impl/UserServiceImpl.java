package com.baizhi.service.impl;

import com.baizhi.dao.UserCountDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import com.baizhi.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Autowired
    UserCountDao userCountDao;



    @Override
    public Map queryAll(int page, int rows) {
        Map map = new HashMap();
        int b=page*rows-rows;
        RowBounds rowBounds = new RowBounds(b,rows);
        List<User> users = userDao.selectByRowBounds(new User(), rowBounds);
        map.put("rows",users);
        map.put("total",userDao.selectAll().size());
        return map;
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUser(User user) {
            userDao.updateByPrimaryKeySelective(user);
    }



    @Override
    public Map selectBySex() {
        Map map=new HashMap();
        List<UserCount> userCounts1 = userCountDao.selectByMale();
        List<UserCount> userCounts2 = userCountDao.selectByFeMale();
        map.put("data1",userCounts1);
        map.put("data2",userCounts2);


        return map;
    }

    @Override
    public Map selectByDate() {
        Map map=new HashMap();
        String a[]={"第一周","第二周","第三周"};
        int b[]={userCountDao.selectByDate(7),userCountDao.selectByDate(14),userCountDao.selectByDate(21)};
        map.put("a",a);
        map.put("b",b);
        return map;
    }

    @Override
    public User login(String name,String password) {
        return  userDao.selectByName(name,password);
    }

    @Override
    public User selectOne(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAllUser() {
        return userDao.selectAll();
    }


}
