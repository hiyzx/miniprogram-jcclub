/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : www.hiyzx.cn:3306
 Source Schema         : jcclub-tmp

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 13/05/2019 23:21:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '公众号标题',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `imageUrl` varchar(250) DEFAULT NULL COMMENT '首页图片地址',
  `articleUrl` varchar(250) DEFAULT NULL COMMENT '公众号文章地址',
  `sequenceNumber` int(11) DEFAULT NULL COMMENT '排序',
  `isShow` tinyint(2) DEFAULT NULL COMMENT '是否展示 0 不展示 1 展示',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='公众号文章信息表';

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` VALUES (1, '第四届中国“互联网+”大学生创新创业大赛港澳台赛事通知', '赛事通知', 'http://t.cn/EMjzcDV', 'https://mp.weixin.qq.com/s/u-o871lfHYvKP6jhyZIV0A', 1, 1, '2019-02-19 11:25:56');
INSERT INTO `article` VALUES (2, '关于举办第三届中国（福建）女大学生（女科技工作者）创新创业大赛的通知', '赛事通知', 'http://t.cn/EMjZYBL', 'https://mp.weixin.qq.com/s/l8K1gTBvGcFt8AgSpCqMZw', 2, 1, '2019-02-19 11:25:56');
INSERT INTO `article` VALUES (3, '关于2018年集美大学第四届“互联网+” 大学生创新创业大赛决赛的通知', '赛事通知', 'http://t.cn/EMjZvJF', 'https://mp.weixin.qq.com/s/zVkL8Ns2BGruyyrxkdpnKg', 3, 1, '2019-02-19 11:25:56');
COMMIT;

-- ----------------------------
-- Table structure for carousel_map
-- ----------------------------
DROP TABLE IF EXISTS `carousel_map`;
CREATE TABLE `carousel_map` (
  `id` int(11) NOT NULL,
  `sequenceNumber` int(11) DEFAULT NULL COMMENT '排序',
  `imageUrl` varchar(250) DEFAULT NULL COMMENT '图片地址',
  `isShow` tinyint(4) DEFAULT NULL COMMENT '是否展示 0 不展示 1展示',
  `createTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='轮播图表';

-- ----------------------------
-- Records of carousel_map
-- ----------------------------
BEGIN;
INSERT INTO `carousel_map` VALUES (1, 1, 'http://localhost:8080/jcclub/img/%E8%BD%AE%E6%92%AD%E5%9B%BE1.jpg', 1, '2019-02-19');
INSERT INTO `carousel_map` VALUES (2, 2, 'http://localhost:8080/jcclub/img/%E8%BD%AE%E6%92%AD%E5%9B%BE2.jpg', 1, '2019-02-19');
INSERT INTO `carousel_map` VALUES (3, 3, 'http://localhost:8080/jcclub/img/%E8%BD%AE%E6%92%AD%E5%9B%BE3.jpg', 1, '2019-02-19');
COMMIT;

-- ----------------------------
-- Table structure for delivery_relationship
-- ----------------------------
DROP TABLE IF EXISTS `delivery_relationship`;
CREATE TABLE `delivery_relationship` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `talentUserInfoId` int(11) DEFAULT NULL COMMENT '投递者用户id',
  `talentId` int(11) DEFAULT NULL COMMENT '人才库id',
  `teamId` int(11) DEFAULT NULL COMMENT '团队库id',
  `teamUserInfoId` int(11) DEFAULT NULL COMMENT '团队创建人id',
  `postId` int(11) DEFAULT NULL COMMENT '岗位id',
  `isPartner` int(4) DEFAULT '0' COMMENT '是否合伙人0不是1是',
  `createTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of delivery_relationship
-- ----------------------------
BEGIN;
INSERT INTO `delivery_relationship` VALUES (1, 5, 10, 7, 4, NULL, 0, '2019-03-15');
INSERT INTO `delivery_relationship` VALUES (5, 12, 8, 7, 8, 1, 1, '2019-05-10');
COMMIT;

-- ----------------------------
-- Table structure for post_requirement
-- ----------------------------
DROP TABLE IF EXISTS `post_requirement`;
CREATE TABLE `post_requirement` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT ' ',
  `userInfoId` int(10) NOT NULL,
  `teamId` int(10) NOT NULL,
  `post` varchar(20) NOT NULL COMMENT '岗位',
  `type` varchar(4) NOT NULL COMMENT '创业，比赛，综合',
  `salary` varchar(16) NOT NULL,
  `requirement` varchar(255) NOT NULL COMMENT '岗位要求，最多127字',
  `isPublish` int(4) DEFAULT NULL COMMENT '是否发布0不 1发布',
  `approvalStatus` int(4) NOT NULL DEFAULT '0' COMMENT '审批状态0待审批 1通过 2拒绝',
  `remark` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_requirement
-- ----------------------------
BEGIN;
INSERT INTO `post_requirement` VALUES (1, 8, 7, '工作', '类型', '100', '介绍', 1, 1, NULL, NULL);
INSERT INTO `post_requirement` VALUES (2, 8, 7, '工作2', '类型2', '200', '介绍2', 1, 2, NULL, NULL);
INSERT INTO `post_requirement` VALUES (3, 8, 7, '工作3', '类型3', '200', '介绍3', 1, 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for talent
-- ----------------------------
DROP TABLE IF EXISTS `talent`;
CREATE TABLE `talent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userInfoId` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `tel` varchar(11) NOT NULL COMMENT '电话',
  `className` varchar(10) DEFAULT NULL COMMENT '班级',
  `idealPost` varchar(20) DEFAULT NULL COMMENT '目标岗位',
  `type` varchar(10) DEFAULT NULL COMMENT '类型',
  `workExperience` varchar(255) DEFAULT NULL COMMENT '工作经历',
  `competitionExperience` varchar(255) DEFAULT NULL COMMENT '竞赛经历',
  `isPublish` int(1) DEFAULT '0' COMMENT '是否发布 0 不发布 1 发布',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='人才信息表';

-- ----------------------------
-- Records of talent
-- ----------------------------
BEGIN;
INSERT INTO `talent` VALUES (5, 7, '贤志兰', '18759271752', '计算1513', '财务', '创业', '无', '无', 1, '2019-03-13 12:54:09');
INSERT INTO `talent` VALUES (7, 8, '刘建华', '18860077407', '六年级一班', '网络工程师', '创', '暂无', '无 ', 1, '2019-03-13 12:56:03');
INSERT INTO `talent` VALUES (8, 12, '叶招兴', '18850341357', '六年级', '开发', '技术', '无', '无', 1, '2019-05-10 22:58:14');
COMMIT;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userInfoId` int(11) DEFAULT NULL COMMENT '创建人id',
  `teamName` varchar(30) DEFAULT NULL COMMENT '团队名称',
  `briefIntro` varchar(255) DEFAULT NULL COMMENT '团队/公司简介（100字）',
  `tel` varchar(12) DEFAULT NULL COMMENT '联系电话',
  `place` varchar(60) DEFAULT NULL COMMENT '地点',
  `remark` varchar(255) DEFAULT NULL,
  `isPublish` int(2) DEFAULT '1' COMMENT '是否发布 1发布 0 未发布',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='团队信息表';

-- ----------------------------
-- Records of team
-- ----------------------------
BEGIN;
INSERT INTO `team` VALUES (7, 8, '小程序测试', '哈哈哈啊哈', '18850341357', '科技园', NULL, 1, '2019-05-10 20:32:53');
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(200) DEFAULT NULL COMMENT '微信唯一识别号',
  `nickName` varchar(20) NOT NULL COMMENT '微信昵称',
  `avatarUrl` varchar(500) NOT NULL COMMENT '微信头像',
  `gender` tinyint(2) NOT NULL COMMENT '性别:1男 2女',
  `isAdmin` int(4) NOT NULL DEFAULT '0' COMMENT '是否管理员0不是 1是',
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户微信信息表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (6, NULL, '浮生若梦', 'https://wx.qlogo.cn/mmopen/vi_32/3foCJ4afrI4PvsaKZKMJV1LjYJTCyF1SgicyUU2xD5ic9ERdGOZEhXbtuR1DvY7MHaDRJRibZnL2m7A4UvMvzjXpA/132', 2, 0, '2019-03-16 00:18:19');
INSERT INTO `user_info` VALUES (8, 'o_D4K40qa_OYb1sNN9AyVC4ZUVrY', '叶招兴', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er4JSGvt4xzcS84dicoEUcnykScXtV4bXWSEZNI10YLjSIuy4b9xc3KBnMyF1bAezTQEv6x3WQvKFA/132', 1, 1, '2019-05-10 20:29:43');
INSERT INTO `user_info` VALUES (11, 'null', '叶招兴', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er4JSGvt4xzcS84dicoEUcnykScXtV4bXWSEZNI10YLjSIuy4b9xc3KBnMyF1bAezTQEv6x3WQvKFA/132', 1, 1, '2019-05-10 21:46:56');
INSERT INTO `user_info` VALUES (12, 'o_D4K40qa_OYb1sNN9AyVC4ZUVrY1', '叶招兴', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er4JSGvt4xzcS84dicoEUcnykScXtV4bXWSEZNI10YLjSIuy4b9xc3KBnMyF1bAezTQEv6x3WQvKFA/132', 1, 0, '2019-05-10 22:56:31');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
