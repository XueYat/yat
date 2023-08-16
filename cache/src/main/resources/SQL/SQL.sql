DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
     `product_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID',
     `name` varchar(1000) NOT NULL COMMENT '商品名称',
     `supplier` varchar(1000) NOT NULL COMMENT '供应商',
     `price` decimal(10, 2) NOT NULL COMMENT '价格',
     `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
     `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
     `create_time` datetime DEFAULT (now()) COMMENT '创建时间',
     `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表';

insert into t_product (name, supplier, price) values ('Mac Book Pro', 'Apple', 15999.0);
insert into t_product (name, supplier, price) values ('Mac Book Air', 'Apple', 12999.0);
insert into t_product (name, supplier, price) values ('Iphone 11', 'Apple', 5999.0);
insert into t_product (name, supplier, price) values ('Iphone 12', 'Apple', 6999.0);
insert into t_product (name, supplier, price) values ('Iphone 13', 'Apple', 7999.0);
insert into t_product (name, supplier, price) values ('Iphone 14', 'Apple', 8999.0);
insert into t_product (name, supplier, price) values ('Iphone 15', 'Apple', 9999.0);