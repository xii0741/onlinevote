DELIMITER //
--刪除投票紀錄
CREATE PROCEDURE DeleteVoteRecordsByItemID(
    IN item_id INT
)
BEGIN
    DELETE FROM 投票紀錄 WHERE 投票項目編號 = item_id;
END //

DELIMITER ;
