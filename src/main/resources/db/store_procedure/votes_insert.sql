DELIMITER //
--投票紀錄寫入
CREATE PROCEDURE InsertVoteRecordWithCheck(
    IN username VARCHAR(255),
    IN itemnum INT
)
BEGIN
    DECLARE record_exists INT;

    --避免重複投票在同一項目
    SELECT COUNT(*) INTO record_exists FROM 投票紀錄 WHERE 投票人 = username AND 投票項目編號 = itemnum;

    IF record_exists = 0 THEN
        INSERT INTO 投票紀錄 (投票人, 投票項目編號) VALUES (username, itemnum);
    END IF;
END//

DELIMITER ;

