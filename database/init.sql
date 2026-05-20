-- 校园图书漂流系统数据库初始化脚本
CREATE DATABASE IF NOT EXISTS book_flow DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE book_flow;

-- 用户表（对应entity/User.java）
CREATE TABLE `user` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `credit_score` INT DEFAULT 100 COMMENT '信用分',
  `role` VARCHAR(20) DEFAULT 'student' COMMENT '角色：student/admin'
);

-- 图书表（对应entity/Book.java）
CREATE TABLE `book` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '图书ID',
  `name` VARCHAR(100) NOT NULL COMMENT '书名',
  `author` VARCHAR(50) COMMENT '作者',
  `category` VARCHAR(30) COMMENT '分类',
  `status` TINYINT DEFAULT 0 COMMENT '0-可借 1-已借出'
);

-- 借阅记录表（对应entity/BorrowRecord.java）
CREATE TABLE `borrow_record` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `book_id` BIGINT NOT NULL COMMENT '图书ID',
  `borrow_time` DATETIME COMMENT '借阅时间',
  `return_time` DATETIME COMMENT '归还时间',
  `status` TINYINT DEFAULT 0 COMMENT '0-借阅中 1-已归还',
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`book_id`) REFERENCES `book`(`id`)
);

-- 测试数据
INSERT INTO `user` (`username`, `password`, `role`) VALUES 
('user01', '123456', 'student'),
('admin', '123456', 'admin');
