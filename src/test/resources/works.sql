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
DROP TABLE IF EXISTS `works`;
CREATE TABLE `works` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classification` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `works` VALUES ('1', '移动业务类', '移动业务类二级菜单', '包含28个工单流程');
INSERT INTO `works` VALUES ('2', '宽固业务类', '宽固业务类二级菜单', '包含28个工单流程');
INSERT INTO `works` VALUES ('3', '渠道管理类', '渠道管理类二级菜单', '包含28个工单流程');
INSERT INTO `works` VALUES ('4', '集客部类', '集客部类二级菜单', '包含28个工单流程');
INSERT INTO `works` VALUES ('5', '客服/营业厅类', '客服/营业厅类二级菜单', '包含28个工单流程');
INSERT INTO `works` VALUES ('6', '一站式支撑类', '一站式支撑类二级菜单', '包含28个工单流程');
INSERT INTO `works` VALUES ('7', '信息化支撑类', '信息化支撑类二级菜单', '包含28个工单流程');
INSERT INTO `works` VALUES ('8', '人力资源部类', '人力资源部类二级菜单', '包含28个工单流程');
SET FOREIGN_KEY_CHECKS=1;
