use onlineVoting;
INSERT INTO 投票項目 (投票項目編號, 投票項目名稱)
VALUES
    (1, '電腦'),
    (2, '滑鼠');
    
INSERT INTO users (username, password)
VALUES
    ('user1', 'user'),
    ('user2', 'user');

INSERT INTO admin (admin_username)
VALUES ('user1');