package com.online_vote.online_vote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.online_vote.online_vote.model.database_access;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class login_controller {
    @Autowired
    database_access database_access;

    //定義user物件
    public class User {
        private String username;
        private String password;
        
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    }

    @PostMapping("/login")//登入請求
    public void login(@RequestBody String req, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        User user = gson.fromJson(req, User.class);

        if (database_access.checkLogin(user.getUsername(), user.getPassword())) {
            // 登入成功，設置Session
            session = request.getSession();
            session.setAttribute("username", user.getUsername());

            // 設置HTTP響應狀態碼為200 OK
            response.sendRedirect("/votepage");
        } else {
            // 重定向到指定URL
            System.out.println("fail");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //response.sendRedirect("/login");
        }
    }
    @GetMapping("/logout")//登出
    public void logout(HttpSession session, HttpServletResponse response) throws IOException{
        session.invalidate();
        response.sendRedirect("/login");
    }
    
}