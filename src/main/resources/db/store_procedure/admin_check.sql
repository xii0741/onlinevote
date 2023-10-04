DELIMITER //
--檢查是否為管理員
CREATE PROCEDURE CheckAdminByUsername(
    IN input_username VARCHAR(255)
)
BEGIN
    DECLARE admin_count INT;
    
    SELECT COUNT(*) INTO admin_count FROM admin WHERE admin_username = input_username;
    
    IF admin_count > 0 THEN
        SELECT 1 AS result;
    ELSE
        SELECT 0 AS result;
    END IF;
END//

DELIMITER ;
