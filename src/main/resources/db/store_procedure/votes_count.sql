DELIMITER //
--計算票數
CREATE PROCEDURE GetVoteCountByItemID(
    IN item_id INT,
    OUT vote_count INT
)
BEGIN
    SELECT COUNT(*) INTO vote_count FROM 投票紀錄 WHERE 投票項目編號 = item_id;
END//

DELIMITER ;
