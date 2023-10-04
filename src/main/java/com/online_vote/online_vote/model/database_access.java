package com.online_vote.online_vote.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.SqlOutParameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Types;

@Repository
public class database_access {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public database_access(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //檢查登入資訊userid password
    public boolean checkLogin(String username, String password) {
        String sql = "CALL check_login(?, ?)";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
        System.out.println(result != null && result == 1);
        return result != null && result == 1;
    }

    //檢查當前用戶是否為管理員
    public boolean adminCheck(String username) {
        String sql = "CALL CheckAdminByUsername(?)";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, username);
        System.out.println(result != null && result == 1);
        return result != null && result == 1;
    }

    //新增投票項目
    public void insertVoteItemWithParams(int item_id, String item_name) {
        String sql = "CALL InsertVoteItemWithParams(?, ?)";
        jdbcTemplate.update(sql, item_id, item_name);
    }

    //刪除投票項目
    public void deleteVoteItemByNum(int itemNum) {
        String sql = "CALL DeleteVoteItemByNum(?)";
        String sql2 = "CALL DeleteVoteRecordsByItemID(?)";
        jdbcTemplate.update(sql, itemNum);//刪除投票項目
        jdbcTemplate.update(sql2, itemNum);//一併刪除投票紀錄，因為投票紀錄只紀錄物件id，所以刪除完項目下物新物件蓋上去投票項目會錯亂
    }

    //取得投票項目列表
    public List<Map<String, Object>> getVoteItems() {
        String sql = "CALL GetVoteItems()";
        List<Map<String, Object>> voteItems = jdbcTemplate.queryForList(sql);
        return voteItems;
    }

    //寫入投票紀錄
    public void insertVoteRecord(String username, int item_num){
        String sql = "CALL InsertVoteRecordWithCheck(?, ?)";
        jdbcTemplate.update(sql, username, item_num);
    }
    
    //計算當前物品的票數
    public int getVoteCountByItemId(int itemId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GetVoteCountByItemID")
                .declareParameters(
                        new SqlParameter("item_id", Types.INTEGER),
                        new SqlOutParameter("vote_count", Types.INTEGER)
                );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("item_id", itemId);

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (int) result.get("vote_count");
    }

    


}
