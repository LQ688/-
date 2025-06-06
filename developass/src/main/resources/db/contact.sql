-- 创建contact表
CREATE TABLE IF NOT EXISTS `contact` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` varchar(50) NOT NULL COMMENT '姓名',
    `phone` varchar(20) NOT NULL COMMENT '电话',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `company` varchar(100) DEFAULT NULL COMMENT '公司',
    `group_type` varchar(50) DEFAULT NULL COMMENT '客户类型',
    `industry` varchar(50) DEFAULT NULL COMMENT '行业',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`),
    KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='联系人表'; 