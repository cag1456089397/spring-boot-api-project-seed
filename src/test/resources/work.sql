/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-06-23 14:25:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `process_type` varchar(255) DEFAULT NULL,
  `safe_level` int(1) DEFAULT 1,
  `work_status` int(1) DEFAULT 1,
  `work_classifition` varchar(255) DEFAULT NULL,
  `urgent_level` varchar(255) DEFAULT NULL,
  `work_theme` varchar(255) DEFAULT NULL,
  `work_content` varchar(255) DEFAULT NULL,
  `high_director` int(11) DEFAULT NULL,
  `service_type` varchar(255)  DEFAULT NULL,
  `apply_derate_amount` float(11) DEFAULT NULL,
  `originalnumber_prestore_amount` float(11) DEFAULT NULL,
  `prettynumber_type` varchar(255) DEFAULT NULL,
  `apply_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
