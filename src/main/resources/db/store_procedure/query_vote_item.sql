DELIMITER //
--查詢投票選項
CREATE PROCEDURE GetVoteItems()
BEGIN
    SELECT 投票項目編號, 投票項目名稱
    FROM 投票項目;
END//

DELIMITER ;
