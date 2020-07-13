-- 导出member_value_table表中的数据至csv文件
select * from `airline`.`member_value_table`
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/Big-data-based-Airline-Site/mysql/data_out/user_value_data_in.csv'
fields terminated by ','
optionally enclosed by '"'
escaped by '"'
lines terminated by '\r\n';