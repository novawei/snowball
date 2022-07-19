/*
 Navicat Premium Data Transfer

 Source Server         : 49.4.3.168_3306
 Source Server Type    : MySQL
 Source Server Version : 50632
 Source Host           : 49.4.3.168:3306
 Source Schema         : snowball_order

 Target Server Type    : MySQL
 Target Server Version : 50632
 File Encoding         : 65001

 Date: 06/05/2022 14:14:12
*/

CREATE DATABASE IF NOT EXISTS `snowball_order` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `snowball_order`;


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
-- Table structure for t_order
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_order` (
  `id` bigint(64) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
