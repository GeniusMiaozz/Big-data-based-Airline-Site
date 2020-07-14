-- 0.进入airline数据库
USE `airline`;

-- 1.导入数据至会员注册信息表 member_register_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/member_register_in.csv'
into table `airline`.`member_register_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 2.导入数据至管理员注册信息表 manager_register_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/manager_register_in.csv'
into table `airline`.`manager_register_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 3.导入数据至会员个人信息表 member_information_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/member_information_data_in.csv'
into table `airline`.`member_information_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 4.导入数据至会员价值信息表 member_value_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/member_value_data_in.csv'
into table `airline`.`member_value_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 5.导入数据至航班查询表 flight_query_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/flight_query_data_in.csv'
into table `airline`.`flight_query_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 6.导入数据至订单管理表 order_manage_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/order_manage_data_in.csv'
into table `airline`.`order_manage_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';