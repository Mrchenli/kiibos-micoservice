CREATE TABLE `KIIBOS_GALT_CATEGORY` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(255) NOT NULL,
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `category_name` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `KIIBOS_GALT_CATEGORY_TREE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL,
  `level` bigint(20) NOT NULL,
  `parent_category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 插入根分类 */
INSERT INTO `KIIBOS_GALT_CATEGORY_TREE`(category_id,level,parent_category_id) VALUES (0, 0, 0);
INSERT INTO `KIIBOS_GALT_CATEGORY` (category_id,creator,category_name) VALUES (0,'sys', 'root');