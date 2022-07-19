/*
 Navicat Premium Data Transfer

 Source Server         : 49.4.3.168_3306
 Source Server Type    : MySQL
 Source Server Version : 50632
 Source Host           : 49.4.3.168:3306
 Source Schema         : snowball_uac

 Target Server Type    : MySQL
 Target Server Version : 50632
 File Encoding         : 65001

 Date: 06/05/2022 14:14:12
*/

CREATE DATABASE `snowball_uac` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `snowball_uac`;


SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- SEATA Global Transaction
-- Table structure for undo_log
-- 注意此处0.3.0+ 增加唯一索引 ux_undo_log
-- ----------------------------
CREATE TABLE IF NOT EXISTS `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_permission` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_user_role` (
  `id` bigint(64) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_user_role` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_role_permission` (
  `id` bigint(64) NOT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  `permission_id` varchar(50) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_role_permission` (`role_id`, `permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


INSERT INTO `t_user`(`id`, `username`, `name`, `password`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('b1d71a0d975958facaff6b3d35d5de05', 'admin', '管理员', '$2a$10$nrLa2r4yy6MNyYj/BxDhPOkIQimURKEomeTYWdpJuigTFg3FBjPmq', 'b1d71a0d975958facaff6b3d35d5de05', '2022-07-19 13:14:20', 'b1d71a0d975958facaff6b3d35d5de05', '2022-07-19 13:14:20');
INSERT INTO `t_role`(`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('ROLE_ADMIN', '管理员', 'b1d71a0d975958facaff6b3d35d5de05', '2022-07-19 13:14:20', 'b1d71a0d975958facaff6b3d35d5de05', '2022-07-19 13:14:20');
INSERT INTO `t_user_role`(`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1549304844017328129, 'b1d71a0d975958facaff6b3d35d5de05', 'ROLE_ADMIN', 'b1d71a0d975958facaff6b3d35d5de05', '2022-07-19 16:07:14', 'b1d71a0d975958facaff6b3d35d5de05', '2022-07-19 16:07:14');


SET FOREIGN_KEY_CHECKS = 1;
