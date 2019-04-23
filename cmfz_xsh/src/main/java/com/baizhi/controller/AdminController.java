package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @RequestMapping("login")
    public String login(Admin admin, HttpSession session){
        Admin a = adminService.queryByName(admin);
        if(a!=null){
            session.setAttribute("a",a);
            return null;
        }else {
            return null;

        }


    }



}
