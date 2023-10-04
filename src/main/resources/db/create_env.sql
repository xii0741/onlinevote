CREATE DATABASE onlineVoting;
USE onlineVoting; 

CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255)
);

CREATE TABLE 投票項目 (
    投票項目編號 INT PRIMARY KEY,
    投票項目名稱 VARCHAR(255)
);

CREATE TABLE 投票紀錄 (
    流水號 INT AUTO_INCREMENT PRIMARY KEY,
    投票人 VARCHAR(255),
    投票項目編號 INT
);

CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    admin_username VARCHAR(255) NOT NULL
);
