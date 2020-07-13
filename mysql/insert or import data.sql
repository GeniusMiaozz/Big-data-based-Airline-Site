-- 0.进入airline数据库
USE `airline`;

-- 1.插入数据至会员注册信息表 member_register_table
INSERT INTO `airline`.`member_register_table` VALUES (1, '13940214862', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (2, '13940214912', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (3, '13940214867', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (4, '13940214745', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (5, '13940214123', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (6, '13940209657', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (7, '13940204521', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (8, '13940207463', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (9, '13940209012', '123456');
INSERT INTO `airline`.`member_register_table` VALUES (10, '13940207512', '123456');

-- 2.插入数据至管理员注册信息表 manager_register_table
INSERT INTO `airline`.`manager_register_table` VALUES (1, '13940229634', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (2, '13940228715', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (3, '13940228463', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (4, '13940227493', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (5, '13940221094', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (6, '13940239512', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (7, '13940230740', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (8, '13940236317', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (9, '13940235917', '123456');
INSERT INTO `airline`.`manager_register_table` VALUES (10, '13940232268', '123456');

-- 3.插入数据至会员个人信息表 member_information_table
INSERT INTO `airline`.`member_information_table` VALUES (1, '男', 34, 120104198605222456, '2006-03-31', 1, '天津', null, '中国', 2);
INSERT INTO `airline`.`member_information_table` VALUES (2, '男', 55, 120104196507451245, '2006-03-31', 2, '天津', null, '中国', 24);
INSERT INTO `airline`.`member_information_table` VALUES (3, '女', 48, 120104197201301259, '2006-04-07', 1, '天津', null, '中国', 9);
INSERT INTO `airline`.`member_information_table` VALUES (4, '男', 29, 120104199103272568, '2006-08-10', 1, '天津', null, '中国', 12);
INSERT INTO `airline`.`member_information_table` VALUES (5, '女', 28, 120104199208152139, '2008-02-07', 1, '天津', null, '中国', 3);

-- 4.导入数据至会员价值信息表 member_value_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/user_value_data_in.csv'
into table `airline`.`member_value_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\r\n';

-- 5.导入数据至航班查询表 flight_query_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/flight_data_in.csv'
into table `airline`.`flight_query_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\r\n';

