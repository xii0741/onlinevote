DELIMITER //
--刪除投票選項
CREATE PROCEDURE DeleteVoteItemByNum(
    IN item_num INT
)
BEGIN
    DELETE FROM 投票項目 WHERE 投票項目編號 = item_num;
END//

DELIMITER ;
