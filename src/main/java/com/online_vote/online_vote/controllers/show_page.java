package com.online_vote.online_vote.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.online_vote.online_vote.model.database_access;

import jakarta.servlet.http.HttpSession;

@Controller
public class show_page {
    @Autowired
    database_access database_access;
     
    @GetMapping("/login")//返回登入畫面html
    public String loginPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return "votepage"; 
        }
        return "login"; 
    }

    @GetMapping("/")//轉跳至投票畫面如果有登入
    public String initalPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return "votepage"; 
        }
        return "login"; 
    }

    @GetMapping("/votepage")//返回投票畫面
    public String votesPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return "votepage"; 
        }
        return "login"; 
    }
    @GetMapping("/admin") //返回工程管理畫面
    public String adminPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if ((username != null) && (database_access.adminCheck(username))) {
            return "admin"; 
        }
        return "votepage"; 
    }

}
