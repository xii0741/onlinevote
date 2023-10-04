DELIMITER //
--登入帳密檢查
CREATE PROCEDURE check_login(
    IN input_username VARCHAR(255),
    IN input_password VARCHAR(255)
)
BEGIN
    DECLARE user_count INT;

    SELECT COUNT(*) INTO user_count
    FROM users
    WHERE username = input_username AND password = input_password;

    IF user_count > 0 THEN
        SELECT 1 AS result;
    ELSE
        SELECT 0 AS result;
    END IF;
END;

//
DELIMITER ;
