-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: salessystem
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `goods_info`
--

DROP TABLE IF EXISTS `goods_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `seller_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '发布此商品的卖家id',
  `title` varchar(80) NOT NULL DEFAULT '' COMMENT '商品名称',
  `image` varchar(100) NOT NULL DEFAULT '' COMMENT '图片',
  `abstract` varchar(140) NOT NULL DEFAULT '' COMMENT '摘要',
  `description` varchar(1000) NOT NULL DEFAULT '' COMMENT '正文描述',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '即时单价',
  `amount` int(10) NOT NULL DEFAULT '0' COMMENT '剩余货量',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_seller` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='商品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_info`
--

LOCK TABLES `goods_info` WRITE;
/*!40000 ALTER TABLE `goods_info` DISABLE KEYS */;
INSERT INTO `goods_info` VALUES (2,2,'英伦风长筒橡胶雨靴','https://yanxuan.nosdn.127.net/020deb595e21adb60ca9bad9198d4433.png','别致美型，立体舒适','穿着你踏过小溪跳过水塘',179.00,1),(3,2,'免安装台式洗碗机','https://yanxuan.nosdn.127.net/629abc5b861f3399497ae52c6440ba94.png','解放双手，让洗碗变得简单','生活那么忙，别把时间花在洗碗上',2599.00,180),(4,2,'网易有道 翻译蛋','https://yanxuan.nosdn.127.net/41826cf94e1c0cc3deb074f68452d8a0.png','二十七种语言 智能语音翻译机','翻译精准，内置有道神经网络翻译',688.00,497),(5,2,'3D曲面软边钢化手机膜','https://yanxuan.nosdn.127.net/f23c78c6cd1c4bc090da165f8e744bd3.png','3D曲面，全面保护屏幕','3D曲面，全包边无缝贴合',14.90,1000),(6,2,'空气净化器','https://yanxuan.nosdn.127.net/1b2e225a81f7be02650c36a8eaf094df.png','杀菌、除甲醛、PM2.5指数实时显示','1.建议每3-6个月更换一次滤芯。\r\n2.在初次启动空气净化器前，请打开面板，拿掉滤网的塑料外袋，合上面板后，再启动机器\r\n3.滤网更换后，长按滤网更换指示按钮5秒进行滤网复位设置。',999.00,299),(7,2,'健康体脂秤','https://yanxuan.nosdn.127.net/1843a33e730e6e3fa046a43d8cf31028.png','超薄材质，精确记录','尺寸:280*280*16.5 MM\r\n材质:ABS环保塑胶+5MM钢化玻璃\r\n测量范围:5-150 KG\r\n净重:约1.5 KG\r\n注意事项:孕妇禁止使用\r\n温馨提示:颜色以具体实物为准',129.00,60),(8,2,'混合果仁 210克','https://yanxuan.nosdn.127.net/ab53c59e7803253850e6eb36ee32f49c.jpg','美味搭配，均衡营养','美丽动仁',52.00,500),(9,2,'西班牙直采 珍藏干红葡萄酒 750毫升','/img/1522051037615.jpg','产区名庄，囊获大奖','温馨提示1、网易严选出售的食品，除明确质量问题外均不接受退换货。\r\n2、请将产品保存于阴凉、通风、干燥处，开瓶后请尽快饮用。\r\n3、适度饮酒怡情，过度饮酒伤身。\r\n4、注意：饮酒后禁止驾驶机动车。\r\n5、本品不对未成年人出售。\r\n6、如果因为极温而导致的破损（顶塞，漏液，漏液导致的污标等），请联系客服，严选核实后会为您赔付',168.00,100);
/*!40000 ALTER TABLE `goods_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-26 16:55:49
