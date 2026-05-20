-- 图书漂流系统数据库
CREATE DATABASE IF NOT EXISTS book_flow;
USE book_flow;

-- 用户表
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    credit_score INT DEFAULT 100
);

-- 图书表
CREATE TABLE book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    author VARCHAR(50),
    status TINYINT DEFAULT 0
);

-- 借阅记录表
CREATE TABLE borrow_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    book_id BIGINT,
    borrow_time DATETIME,
    status TINYINT DEFAULT 0
);
