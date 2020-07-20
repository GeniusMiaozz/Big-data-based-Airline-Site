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

-- 7.导入数据至增值服务表 additional_service_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/add_service_data_in.csv'
into table `airline`.`additional_service_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 8.导入数据至会员积分明细表 points_details_table


-- 9.导入数据至会员积分兑换表 points_exchange_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/flight_exchange_data_in.csv'
into table `airline`.`points_exchange_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 10.导入数据至用户组内推荐使用的地点信息表 recommend_city_table
load data infile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_in/recommend_city_data_in.csv'
into table `airline`.`recommend_city_table`
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';