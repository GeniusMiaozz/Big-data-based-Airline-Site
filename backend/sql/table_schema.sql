create table Member_Register(
	MEMBER_NO bigint not null primary key,
	TELEPHONE bigint not null,
	PASSWORD text not null
);

create table Flight_Query(
	COMPANY text not null,
	FLIGHT_NO varchar(10) not null,
	DATE date not null,
	DEP_CT text not null,
	DEP_AIRPORT text not null,
	ARR_CT text not null,
	ARR_AIRPORT text not null,
	DEP_TERMINAL varchar(5) not null,
	ARR_TERMINAL varchar(5) not null,
	DEP_SCHEDULED datetime not null,
	ARR_SCHEDULED datetime not null,
	E_PRICE float not null,
	E_TICKETS int not null,
	E_DISCOUNT float not null,
	S_PRICE float not null,
	S_TICKETS int not null,
	S_DISCOUNT float not null,
	F_PRICE float not null,
	F_TICKETS int not null,
	F_DISCOUNT float not null,
	primary key(FLIGHT_NO, DATE)
);
insert into Flight_Query values
('东方航空', 'MU201', '2020-7-11', '上海', '上海浦东国际机场', '伦敦', '伦敦盖特威克机场', 
'T1', 'N', '2020-7-11 01:15:00', '2020-7-11 06:40:00', 10000, 100, 0.9, 12000, 50, 
0.83, 15000, 30, 0.7);