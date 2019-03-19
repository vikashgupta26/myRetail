DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` BIGINT(11) NOT NULL ,
  `price` float(11) NOT NULL,
  `curr_code` varchar(255) NOT NULL DEFAULT 'USD',
  PRIMARY KEY (`id`)
);

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `product` (`id`, `price`) values (13860428,13.49);
INSERT INTO `product` (`id`, `price`) values (16483589,11.32);
INSERT INTO `product` (`id`, `price`) values (16696652,10.82);
INSERT INTO `product` (`id`, `price`) values (16752456,21.72);
INSERT INTO `product` (`id`, `price`) values (15643793,31.52);
INSERT INTO `product` (`id`, `price`) values (15117729,13.46);


/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

