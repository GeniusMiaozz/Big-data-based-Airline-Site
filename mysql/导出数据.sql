-- 导出member_value_table表中的数据至csv文件
select * from member_value_table
into outfile 'C:/Users/miaoz/Desktop/NEU Soft Big Data/AirLine/mysql_data_out/air_data.csv'
fields terminated by ','
optionally enclosed by '"'
escaped by '"'
lines terminated by '\r\n';