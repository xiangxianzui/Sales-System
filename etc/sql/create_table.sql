use `salessystem`;

set names utf8;

CREATE TABLE `user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT 'MD5密码',
  `uniqcode` varchar(50) NOT NULL DEFAULT '' COMMENT '用户唯一标识码',
  `usertype` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '用户电话',
  `address` varchar(50) NOT NULL DEFAULT '' COMMENT '用户地址',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uniq_nickname` (`nickname`),
  UNIQUE INDEX `uniq_uniqcode` (`uniqcode`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE `goods_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `seller_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '发布此商品的卖家id',
  `title` varchar(80) NOT NULL default '' COMMENT '商品名称',
  `image` varchar(100) NOT NULL default '' COMMENT '图片',
  `abstract` varchar(140) NOT NULL default '' COMMENT '摘要',
  `description` varchar(1000) NOT NULL default '' COMMENT '正文描述',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '即时单价',
  `amount` int(10) NOT NULL DEFAULT 0 COMMENT '剩余货量',
  PRIMARY KEY (`id`),
  INDEX `idx_title` (`title`),
  INDEX `idx_seller` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

CREATE TABLE `order_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `buyer_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '买家id',
  `seller_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '卖家id',
  `goods_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品id',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '成交单价',
  `amount` int(10) NOT NULL DEFAULT 0 COMMENT '成交货量',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '订单创建时间',
  `pay_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '订单支付时间',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  INDEX `idx_buyer` (`buyer_id`),
  INDEX `idx_seller` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单表'