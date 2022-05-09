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

DROP DATABASE IF EXISTS `snowball_order`;
CREATE DATABASE `snowball_order` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `snowball_order`;


SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(64) NOT NULL,
  `user_id` bigint(64) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
