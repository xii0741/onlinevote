DELIMITER //
--新稱投票項目
CREATE PROCEDURE InsertVoteItemWithParams(
    IN item_id INT,
    IN item_name VARCHAR(255)
)
BEGIN
    INSERT INTO 投票項目 (投票項目編號, 投票項目名稱)
    VALUES (item_id, item_name);
END//

DELIMITER ;