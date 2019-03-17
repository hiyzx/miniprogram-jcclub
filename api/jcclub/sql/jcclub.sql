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
-- Table structure for delivery_relationship
-- ----------------------------
DROP TABLE IF EXISTS `delivery_relationship`;
CREATE TABLE `delivery_relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `talentUserInfoId` int(11) DEFAULT NULL COMMENT '投递者用户id',
  `talentId` int(11) DEFAULT NULL COMMENT '人才库id',
  `teamId` int(11) DEFAULT NULL COMMENT '团队库id',
  `teamUserInfoId` int(11) DEFAULT NULL COMMENT '团队创建人id',
  `createTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='人才投递关系表';

-- ----------------------------
-- Table structure for talent
-- ----------------------------
DROP TABLE IF EXISTS `talent`;
CREATE TABLE `talent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userInfoId` int(11) DEFAULT NULL COMMENT '用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `tel` varchar(255) DEFAULT NULL COMMENT '电话',
  `className` varchar(255) DEFAULT NULL COMMENT '班级',
  `idealPost` varchar(255) DEFAULT NULL COMMENT '目标岗位',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `workExperience` varchar(255) DEFAULT NULL COMMENT '工作经历',
  `competitionExperience` varchar(255) DEFAULT NULL COMMENT '竞赛经历',
  `isPublish` int(2) DEFAULT '0' COMMENT '是否发布 0 不发布 1 发布',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='人才信息表';

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userInfoId` int(11) DEFAULT NULL COMMENT '创建人id',
  `teamName` varchar(255) DEFAULT NULL COMMENT '团队名称',
  `post` varchar(255) DEFAULT NULL COMMENT '岗位',
  `type` varchar(255) DEFAULT NULL COMMENT '招聘类型',
  `salary` varchar(255) DEFAULT NULL COMMENT '薪资',
  `briefIntro` varchar(255) DEFAULT NULL COMMENT '团队/公司简介（100字）',
  `requirement` varchar(255) DEFAULT NULL COMMENT '招聘要求',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `place` varchar(255) DEFAULT NULL COMMENT '地点',
  `isPublish` int(4) DEFAULT '1' COMMENT '是否发布',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='团队信息表';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(20) DEFAULT NULL COMMENT '微信唯一识别号',
  `nickName` varchar(20) NOT NULL COMMENT '微信昵称',
  `avatarUrl` varchar(500) NOT NULL COMMENT '微信头像',
  `gender` tinyint(2) NOT NULL COMMENT '性别:1男 2女',
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户微信信息表';

SET FOREIGN_KEY_CHECKS = 1;
