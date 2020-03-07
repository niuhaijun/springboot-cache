/*
 Navicat MySQL Data Transfer

 Source Server         : localdb
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : spring_boot_cache

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 15/04/2019 16:17:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for spring_boot_cache_user
-- ----------------------------
DROP TABLE IF EXISTS `spring_boot_cache_user`;
CREATE TABLE `spring_boot_cache_user`
(
    `uuid`        varchar(32) NOT NULL COMMENT '唯一ID',
    `username`    varchar(20)                                            DEFAULT NULL,
    `password`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `age`         int(11)                                                DEFAULT NULL,
    `sex`         int(11)                                                DEFAULT NULL,
    `create_time` timestamp   NULL                                       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `update_time` timestamp   NULL                                       DEFAULT NULL,
    PRIMARY KEY (`uuid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of spring_boot_cache_user
-- ----------------------------
BEGIN;
INSERT INTO `spring_boot_cache_user`
VALUES ('31F8FFF29C37464DB9EDF14D7B769AE6', '1', '1', 13, 1, '2019-04-15 15:27:51',
        '2019-04-15 15:27:51');
INSERT INTO `spring_boot_cache_user`
VALUES ('8B6F428ED7CA45FBBDC58B287C71E483', '4', '4', 17, 1, '2019-04-15 16:01:01',
        '2019-04-15 16:01:01');
INSERT INTO `spring_boot_cache_user`
VALUES ('B4882731888341B1A898255AF6B91958', '牛海军', '3', 13, 0, '2019-04-15 15:15:18',
        '2019-04-15 15:15:19');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
