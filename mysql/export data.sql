-- 0.导出member_value_table表中的数据
select * from `airline`.`member_register_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/member_register_data_out.csv'
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 1.导出manager_register_table表的数据
select * from `airline`.`manager_register_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/manager_register_data_out.csv' 
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 2.导出member_information_table表的数据
select * from `airline`.`member_information_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/member_information_data_out.csv'
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 3.导出member_value_table表中的数据
select * from `airline`.`member_value_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/member_value_data_out.csv'
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 4.导出flight_query_table表中的数据
select * from `airline`.`flight_query_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/flight_query_data_out.csv'
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 5.导出order_manage_table表中的数据
select * from `airline`.`order_manage_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/order_manage_data_out.csv'
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 6.导出additional_service_table表中的数据
select * from `airline`.`additional_service_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/additional_service_data_out.csv'
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 7.导出points_details_table表中的数据
select * from `airline`.`points_details_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/points_details_data_out.csv'
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';

-- 8.导出points_exchange_table表中的数据
select * from `airline`.`points_exchange_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/flight_exchange_data_out.csv'
fields terminated by ',' optionally enclosed by '"' escaped by '"'
lines terminated by '\n';
