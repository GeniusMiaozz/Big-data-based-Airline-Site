-- 0.创建airline数据库
CREATE DATABASE `airline`;

-- 1.进入airline数据库
USE `airline`;

-- 2.创建会员注册信息表 member_register_table
CREATE TABLE `airline`.`member_register_table` (
  `MEMBER_NO` BIGINT UNSIGNED NOT NULL,		-- 会员卡号
  `TELEPHONE` BIGINT NOT NULL,				-- 会员手机号
  `PASSWORD` VARCHAR(16) NOT NULL,			-- 会员登录密码,最大长度为16位
  PRIMARY KEY (`MEMBER_NO`));

-- 3.创建管理员注册信息表 manager_register_table
CREATE TABLE `airline`.`manager_register_table` (
  `MANAGER_NO` BIGINT UNSIGNED NOT NULL,	-- 管理员ID
  `TELEPHONE` BIGINT NOT NULL,				-- 管理员手机号
  `PASSWORD` VARCHAR(16) NOT NULL,			-- 管理员登录密码,最大长度为16位
  PRIMARY KEY (`MANAGER_NO`));

-- 4.创建会员个人信息表 member_information_table
CREATE TABLE `airline`.`member_information_table` (
  `MEMBER_NO` BIGINT UNSIGNED NOT NULL,		-- 会员卡号
  `GENDER` CHAR(1) NULL,					-- 性别
  `AGE` INT UNSIGNED NULL,					-- 年龄
  `FFP_DATE` DATE NOT NULL,					-- 入会时间
  `FFP_TIER` INT UNSIGNED NOT NULL,			-- 会员卡级别
  `WORK_CITY` TEXT NULL,					-- 工作地城市
  `WORK_PROVINCE` TEXT NULL,				-- 工作地省份
  `WORK_COUNTRY` TEXT NULL,					-- 工作地国家
  `FLIGHT_COUNT` INT UNSIGNED NOT NULL,		-- 飞行次数(动态)
  PRIMARY KEY (`MEMBER_NO`),
  CONSTRAINT `Foreign_1`
    FOREIGN KEY (`MEMBER_NO`)
    REFERENCES `airline`.`member_register_table` (`MEMBER_NO`)
    ON DELETE CASCADE						-- 设置级联更新及删除
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- 5.创建会员价值信息表 member_value_table
CREATE TABLE `airline`.`member_value_table` (
  `MEMBER_NO` BIGINT UNSIGNED NOT NULL,		-- 会员卡号
  `FFP_DATE` DATE NOT NULL,					-- 入会时间
  `LOAD_TIME` DATE NOT NULL,				-- 观测窗口结束时间
  `LAST_TO_END` INT UNSIGNED NOT NULL,		-- 最后一次乘机时间至窗口末端的时长
  `FLIGHT_COUNT` INT UNSIGNED NOT NULL,		-- 飞行次数
  `SEG_KM_SUM` INT UNSIGNED NOT NULL,		-- 观测窗口总飞行公里数
  `AVG_DISCOUNT` DOUBLE NOT NULL,			-- 平均折扣率
  `TYPE` VARCHAR(6) NULL,					-- 用户类型
  PRIMARY KEY (`MEMBER_NO`));

-- 6.创建航班查询表 flight_query_table
CREATE TABLE `airline`.`flight_query_table` (
  `COMPANY` TEXT NOT NULL,					-- 公司
  `FLIGHT_NO` VARCHAR(10) NOT NULL,			-- 航班号
  `DATE` DATE NOT NULL,						-- 日期
  `DEP_CT` TEXT NOT NULL,					-- 起飞城市
  `DEP_AIRPORT` TEXT NOT NULL,				-- 起飞机场代号
  `ARR_CT` TEXT NOT NULL,					-- 到达城市
  `ARR_AIRPORT` TEXT NOT NULL,				-- 到达机场代号
  `DEP_TERMINAL` VARCHAR(5) NOT NULL,		-- 起飞航站楼
  `ARR_TERMINAL` VARCHAR(5) NOT NULL,		-- 到达航站楼
  `DEP_SCHEDULED` DATETIME NOT NULL,		-- 预计起飞时间
  `ARR_SCHEDULED` DATETIME NOT NULL,		-- 预计到达时间
  `E_PRICE` FLOAT NOT NULL,					-- 低价票票价
  `E_DISCOUNT` FLOAT NOT NULL,				-- 低价票折扣
  `E_TICKETS` INT NOT NULL,					-- 低价票票数
  `S_PRICE` FLOAT NOT NULL,					-- 中价票票价
  `S_DISCOUNT` FLOAT NOT NULL,				-- 中价票折扣
  `S_TICKETS` INT NOT NULL,					-- 中价票票数
  `F_PRICE` FLOAT NOT NULL,					-- 高价票票价
  `F_DISCOUNT` FLOAT NOT NULL,				-- 高价票折扣
  `F_TICKETS` INT NOT NULL,					-- 高价票票数
  `BAGGAGE_TOKEN` FLOAT NOT NULL,			-- 行李托运开销
  `ACCIDENT_INSURANCE_TOKEN` FLOAT NOT NULL,-- 航空意外险开销
  `DUTY_INSURANCE_TOKEN` FLOAT NOT NULL,	-- 航空责任险开销
  `DELAY_INSURANCE_TOKEN` FLOAT NOT NULL,	-- 航空延误险开销
  `FOOD_TOKEN` FLOAT NOT NULL,				-- 旅行餐饮开销
  PRIMARY KEY (`FLIGHT_NO`, `DATE`));

-- 7.创建订单管理表 order_manage_table
CREATE TABLE `airline`.`order_manage_table` (
  `ORDER_NUMBER` BIGINT UNSIGNED NOT NULL,	-- 订单号
  `FLIGHT_NO` VARCHAR(10) NOT NULL,			-- 航班号
  `DATE` DATE NOT NULL,						-- 日期
  `SEAT_LEVEL` VARCHAR(1) NOT NULL,         -- 座位等级
  `MEMBER_NO` BIGINT UNSIGNED NOT NULL,		-- 会员卡号
  `REFUND_OR_CHANGE` INT UNSIGNED NOT NULL,	-- 退票、改签的标志位
  `POINT` BIGINT NOT NULL,					-- 订单积分
  `PRICE` INT NOT NULL,              		-- 机票原价
  `DISCOUNT` FLOAT NOT NULL,                -- 购买时折扣
  PRIMARY KEY (`ORDER_NUMBER`),
  INDEX `Foreign_idx` (`FLIGHT_NO` ASC, `DATE` ASC, `MEMBER_NO` ASC) VISIBLE,
  CONSTRAINT `Foreign_2`
    FOREIGN KEY (`FLIGHT_NO` , `DATE`)
    REFERENCES `airline`.`flight_query_table` (`FLIGHT_NO` , `DATE`)
    ON DELETE NO ACTION						-- 对于历史订单,可以不设置级联删除
    ON UPDATE NO ACTION,
  CONSTRAINT `Foreign_2_1`
    FOREIGN KEY (`MEMBER_NO`)
    REFERENCES `airline`.`member_register_table` (`MEMBER_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- 8.创建增值服务表 additional_service_table
CREATE TABLE `airline`.`additional_service_table` (
  `ORDER_NUMBER` BIGINT UNSIGNED NOT NULL,	-- 订单号
  `BAGGAGE` TEXT NULL,						-- 行李托运信息
  `INSURANCE` TEXT NULL,					-- 保险信息
  `FOOD` TEXT NULL,							-- 餐饮信息
  PRIMARY KEY (`ORDER_NUMBER`),
  CONSTRAINT `Foreign_3`
    FOREIGN KEY (`ORDER_NUMBER`)
    REFERENCES `airline`.`order_manage_table` (`ORDER_NUMBER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- 9.创建会员积分明细表 points_details_table
CREATE TABLE `airline`.`points_details_table` (
  `MEMBER_NO` BIGINT UNSIGNED NOT NULL,		-- 用户会员卡号
  `SEG_KM_SUM` INT UNSIGNED NOT NULL,		-- 总飞行公里数
  `EXCHANGE_COUNT` INT UNSIGNED NOT NULL,	-- 积分兑换次数
  `REMAIN_POINT` INT UNSIGNED NOT NULL,		-- 剩余积分
  `POINTS_SUM` INT UNSIGNED NOT NULL,		-- 总累计积分
  PRIMARY KEY (`MEMBER_NO`),
  CONSTRAINT `Foreign_5`
    FOREIGN KEY (`MEMBER_NO`)
    REFERENCES `airline`.`member_register_table` (`MEMBER_NO`)
    ON DELETE CASCADE						-- 设置级联更新及删除
    ON UPDATE CASCADE);

-- 10.创建会员积分兑换表 points_exchange_table
CREATE TABLE `airline`.`points_exchange_table` (
  `FLIGHT_NO` VARCHAR(10) NOT NULL,			-- 兑换航班号
  `POINTS_NEED` INT UNSIGNED NOT NULL,		-- 兑换所需积分
  `DEP_CT` TEXT NOT NULL,					-- 起飞城市
  `ARR_CT` TEXT NOT NULL,					-- 到达城市
  `DEP_TIME` DATETIME NOT NULL,				-- 起飞时间
  `ARR_TIME` DATETIME NOT NULL,				-- 到达时间
  PRIMARY KEY (`FLIGHT_NO`));