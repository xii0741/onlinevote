package com.online_vote.online_vote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.online_vote.online_vote.model.database_access;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class voteItem_controller {
    @Autowired
    database_access database_access;

    @GetMapping("/voteitem")
    @ResponseBody 
    public ResponseEntity<List<Map<String, Object>>> voteItemPage(HttpSession session, HttpServletResponse response) throws IOException{
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("/login");
        }

        List<Map<String, Object>> voteItems = database_access.getVoteItems();

        return new ResponseEntity<>(voteItems, HttpStatus.OK);
    }

    public class modifyQuery{
        private int item_num;
        private String item_name;
        private String behave;

        public int getItemNum() {
            return item_num;
        }
    
        public String getItemName() {
            return item_name;
        }
        
        public String getBehave() {
            return behave;
        }
    }

    @PostMapping("/voteitem")
    public void login(@RequestBody String req, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = (String) session.getAttribute("username");
        if ((username != null) && (database_access.adminCheck(username))) {
            Gson gson = new Gson();
            modifyQuery modifyQuery = gson.fromJson(req, modifyQuery.class);
            if(modifyQuery.getBehave().equals("add")){
                database_access.insertVoteItemWithParams(modifyQuery.getItemNum(), modifyQuery.getItemName());
            } 
            if(modifyQuery.getBehave().equals("delete")){
                database_access.deleteVoteItemByNum(modifyQuery.getItemNum());
            }
        }
    }
    
}
