/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : pay

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2019-04-29 15:49:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for task_info
-- ----------------------------
DROP TABLE IF EXISTS `task_info`;
CREATE TABLE `task_info` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(32) DEFAULT NULL,
  `task_group` varchar(32) DEFAULT NULL,
  `class_name` varchar(128) DEFAULT NULL,
  `cron` varchar(32) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `last_exec_time` datetime DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_info
-- ----------------------------
INSERT INTO `task_info` VALUES ('1', 'PrintTimeJob', 'PrintTimeJob', 'com.dy.learn.learn.Tasks.demo.PrintTimeJob', '0 0/1 * * * ?', 'Normal', '2019-04-29 15:45:00', null);
INSERT INTO `task_info` VALUES ('2', 'HelloJob', 'HelloJob', 'com.dy.learn.learn.Tasks.demo.HelloJob', '0 0/1 * * * ?', 'Normal', '2019-04-29 15:45:00', null);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `account` varchar(32) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `sign` varchar(32) DEFAULT NULL,
  `user_type` varchar(32) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'xxx', 'xxsx', null, null, null, null, null, null, null);
