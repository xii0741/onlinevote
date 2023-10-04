package com.online_vote.online_vote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.online_vote.online_vote.model.database_access;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

@RestController
public class votes_controller {
    @Autowired
    database_access database_access;

    @GetMapping("/votes")//取得當前物件的投票資料/votes?item_id的形式傳入並回傳票數
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> voteRecordQuery(@RequestParam("item_id") int itemID, HttpSession session, HttpServletResponse response) throws IOException {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("/login");
        }
        int voteCount = database_access.getVoteCountByItemId(itemID);

        Map<String, Object> result = new HashMap<>();
        result.put("vote_count", voteCount);

        return new ResponseEntity<>(Collections.singletonList(result), HttpStatus.OK);
    }

    class voteQuery{
        private int item_id;
        public int getItem_num() {
            return item_id;
        }
    }
    @PostMapping("/votes")//投票紀錄寫入
    public void votes(@RequestBody String req, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            Gson gson = new Gson();
            voteQuery voteQuery = gson.fromJson(req, voteQuery.class);
            database_access.insertVoteRecord(username, voteQuery.getItem_num());
        }
    }
    
}