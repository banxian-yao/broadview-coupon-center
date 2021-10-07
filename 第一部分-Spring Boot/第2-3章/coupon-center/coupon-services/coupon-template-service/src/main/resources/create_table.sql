CREATE DATABASE IF NOT EXISTS broadview_coupon_db;

-- 创建 coupon_template 数据表
DROP TABLE IF EXISTS  `broadview_coupon_db`.`coupon_template`;

CREATE TABLE IF NOT EXISTS `broadview_coupon_db`.`coupon_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Primary ID',
  `available` boolean NOT NULL DEFAULT false COMMENT '是否是可用状态; true: 可用, false: 不可用',
  `expired` boolean NOT NULL DEFAULT false COMMENT '是否过期; true: 是, false: 否',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '优惠券显示名称',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述信息',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '优惠券类型',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '发券总数',
  `shop_id` bigint(20) COMMENT '适用门店，如果null则是全店通用',
  `created_time` datetime NOT NULL DEFAULT '2021-01-01 00:00:00' COMMENT '创建',
  `rule` varchar(2000) NOT NULL DEFAULT '' COMMENT '优惠券规则宽字段',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='优惠券模板';

DROP TABLE if exists  `broadview_coupon_db`.`coupon` ;
CREATE TABLE IF NOT EXISTS `broadview_coupon_db`.`coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `template_id` int(20) NOT NULL DEFAULT '0' COMMENT '模板ID',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '申请用户ID',
  `created_time` datetime NOT NULL DEFAULT '2021-01-01 00:00:00' COMMENT '创建时间',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '优惠券的状态',
  `shop_id` bigint(20) COMMENT '冗余字段，适用门店，如果null则是全店通用',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_template_id` (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='优惠券实体';