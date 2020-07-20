package func

import org.apache.spark.sql.SparkSession

import scala.collection.mutable.{ArrayBuffer, Map}
import scala.util.Random

object format_func {

  //根据出发时间到达时间,确定到达日期
  def get_arr_date(dep_time: String, arr_time: String, dep_date: String): String = {
    if (arr_time.split(":")(0).toInt < dep_time.split(":")(0).toInt) {
      //如果出发时间的小时大于到达时间的小时,设定为跨夜航班
      if ((dep_date.split("-")(1) == "04") || (dep_date.split("-")(1) == "06") ||
        (dep_date.split("-")(1) == "09") || (dep_date.split("-")(1) == "11")) {
        //判断4,6,9,11月
        if (dep_date.split("-")(2) == "30") {
          //月底,30号
          val arr_date = dep_date.split("-")(0) + '-' +
            (dep_date.split("-")(1).toInt + 1) + "-01"
          arr_date
        }
        else {
          //非月底,日期+1
          val arr_date = dep_date.split("-")(0) + '-' +
            dep_date.split("-")(1) + '-' +
            (dep_date.split("-")(2).toInt + 1).toString
          arr_date
        }
      }
      else if (dep_date.split("-")(1) == "02") {
        if (((dep_date.split("-")(0).toInt % 4 == 0) && (dep_date.split("-")(0).toInt % 100 != 0)) ||
          (dep_date.split("-")(0).toInt % 400 == 0)) {
          //闰年
          if (dep_date.split("-")(2) == 29) {
            //月底
            val arr_date = dep_date.split("-")(0) + "-03-01"
            arr_date
          }
          else {
            //非月底
            val arr_date = dep_date.split("-")(0) + '-' +
              dep_date.split("-")(1) + '-' +
              (dep_date.split("-")(2).toInt + 1).toString
            arr_date
          }
        }
        else {
          //非闰年
          if (dep_date.split("-")(2) == 28) {
            //月底
            val arr_date = dep_date.split("-")(0) + "-03-01"
            arr_date
          }
          else {
            //非月底
            val arr_date = dep_date.split("-")(0) + '-' +
              dep_date.split("-")(1) + '-' +
              (dep_date.split("-")(2).toInt + 1).toString
            arr_date
          }
        }
      }
      else {
        //1,3,5,7,8,9,10,12月
        if (dep_date.split("-")(2) == "31") {
          //月底,31号
          val arr_date = dep_date.split("-")(0) + '-' +
            (dep_date.split("-")(1).toInt + 1) + "-01"
          arr_date
        }
        else {
          //非月底,日期+1
          val arr_date = dep_date.split("-")(0) + '-' +
            dep_date.split("-")(1) + '-' +
            (dep_date.split("-")(2).toInt + 1).toString
          arr_date
        }
      }
    }
    else {
      //非跨夜航班,直接返回出发日期
      val arr_date = dep_date
      arr_date
    }
  }

  //根据出发时间和到达时间,生成票价/积分
  def get_token(dep_time: String, arr_time: String, coef: Int, addi: Int): String = {
    val hour = if (arr_time.split(":")(0).toInt >= dep_time.split(":")(0).toInt)
      arr_time.split(":")(0).toInt - dep_time.split(":")(0).toInt
    else arr_time.split(":")(0).toInt + 24 - dep_time.split(":")(0).toInt

    val minute = if (arr_time.split(":")(1).toInt >= dep_time.split(":")(1).toInt)
      arr_time.split(":")(1).toInt - dep_time.split(":")(1).toInt
    else arr_time.split(":")(1).toInt + 60 - dep_time.split(":")(1).toInt

    val time = hour.toFloat + minute.toDouble / 60

    val result = time * coef + Random.nextInt(addi)
    result.toInt.toString
  }

  //生成不重复随机的手机尾号
  def make_tele_number(n: Int, range: Int): List[String] = {
    val tele_head = Map(0 -> "139", 1 -> "187", 2 -> "158", 3 -> "138", 4 -> "185")
    val arr = ArrayBuffer.empty[String]
    while (arr.size < n) {
      val tele_tail = (Random.nextInt(range) + 10000000).toString
      val tele = tele_head(Random.nextInt(5)) + tele_tail
      if (!arr.contains(tele)) {
        arr += tele
      }
    }
    arr.toList
  }

  //格式化航班查询数据并写入
  def format_flight_data(ss: SparkSession): Unit = {
    val get_company = Map("QD" -> "JC航空", "EO" -> "荷瓦波拉航空", "QW" -> "青岛航空", "MF" -> "厦门航空", "EU" -> "成都航空", "KA" -> "国泰港龙航空",
      "LT" -> "龙江航空", "SJ" -> "自由航空", "JR" -> "幸福航空", "G5" -> "华夏航空", "FU" -> "福州航空", "O3" -> "顺丰航空", "KJ" -> "仁川航空", "TV" -> "西藏航空",
      "VZ" -> "个人旅游航空", "GS" -> "天津航空", "UQ" -> "乌鲁木齐航空", "GT" -> "GB航空", "OQ" -> "重庆航空", "ZH" -> "深圳航空", "KR" -> "卡尔航空", "SC" -> "山东航空",
      "DZ" -> "东海航空", "GX" -> "北部湾航空", "FE" -> "远东航空", "HO" -> "吉祥航空", "GY" -> "多彩贵州航空", "LQ" -> "几内亚航空", "MU" -> "中国东方航空", "HU" -> "海南航空",
      "PN" -> "西部航空", "Y8" -> "金鹏航空", "YG" -> "南部航空", "FM" -> "上海航空", "NS" -> "河北航空", "TR" -> "老虎航空", "CZ" -> "中国南方航空", "KN" -> "中国联合航空",
      "NX" -> "澳门航空", "GJ" -> "长龙航空", "IO" -> "伊尔航空", "DR" -> "瑞丽航空", "JD" -> "首都航空", "CF" -> "中国邮政航空", "CA" -> "中国国际航空", "BL" -> "波兰航空",
      "BK" -> "奥凯航空", "B7" -> "立荣航空", "AQ" -> "九元航空", "AE" -> "华信航空", "9H" -> "长安航空", "9C" -> "春秋航空", "B7" -> "立荣航空", "8L" -> "祥鹏航空", "3U" -> "四川航空")

    val flight_info = ss.sparkContext.textFile("data/flight_data.csv").map(_.split(","))
      .map(row => get_company(row(0).substring(0, 2)) + ',' + row(0) + ',' + row(1) + ',' //公司,航班号,日期
        + row(2) + ',' + row(2) + "机场," //起飞城市,起飞机场
        + row(3) + ',' + row(3) + "机场," //到达城市,到达机场
        + "T" + (Random.nextInt(3) + 1).toString + ',' //起飞航站楼
        + "T" + (Random.nextInt(3) + 1).toString + ',' //到达航站楼
        + row(1) + " " + row(4) + ',' //起飞时间
        + get_arr_date(row(4), row(5), row(1)) + " " + row(5) + ',' //到达时间
        + get_token(row(4), row(5), 750, 150) //低价票票价
        + "," + "0." + (10 + Random.nextInt(30)).toString + ',' + Random.nextInt(181).toString + ',' //低价票座位数,折扣系数
        + get_token(row(4), row(5), 1250, 250) //中价票票价
        + "," + "0." + (20 + Random.nextInt(40)).toString + ',' + Random.nextInt(51).toString + ',' //中价票座位数,折扣系数
        + get_token(row(4), row(5), 1750, 350) //高价票票价
        + "," + "0." + (30 + Random.nextInt(50)).toString + ',' + Random.nextInt(21).toString + ',' //高价票座位数,折扣系数
        + get_token(row(4), row(5), 15, 10) + ',' + get_token(row(4), row(5), 5, 1) + ','
        + get_token(row(4), row(5), 3, 1) + ',' + get_token(row(4), row(5), 2, 1) + ','
        + (15 + Random.nextInt(30)).toString)
    flight_info.saveAsTextFile("output/flight_query")
    //flight_info.foreach(println)
  }

  //格式化客户及管理员的注册信息并写入
  def format_people_data(ss: SparkSession): Unit = {
    val tele_arr = make_tele_number(400, 55889999)
    val member_regi = ss.sparkContext.textFile("data/people_data.csv").map(_.split(","))
      .map(row => row(0) + ',' + tele_arr(row(0).toInt - 1) + ",user" + row(1))
    member_regi.saveAsTextFile("output/member_register")
    //member_info.foreach(println)

    val manager_regi = ss.sparkContext.textFile("data/people_data.csv").map(_.split(","))
      .map(row => row(0) + ',' + tele_arr(row(0).toInt + 200 - 1) + ",sys" + row(1))
    manager_regi.saveAsTextFile("output/manager_register")
    //manager_info.foreach(println)
  }

  //格式化客户个人信息并写入
  def format_member_info_data(ss: SparkSession): Unit = {
    val sex = Map(0 -> "男", 1 -> "女")
    val member_info = ss.sparkContext.textFile("data/member_data.csv").map(_.split(","))
      .map(row => row(0) + ',' + sex(row(3).toInt % 2) + ',' + (2020 - row(1).split("-")(0).toInt + 18).toString + ',' //会员号,性别,年龄
        + row(4) + ',' + (row(8).toInt / 15 + 1).toString + ',' //入会时间,会员卡级别
        + row(5) + ',' + row(6) + ",中国," + row(8)) //城市,省份,国家,飞行次数
    member_info.saveAsTextFile("output/member_information")
    //member_info.foreach(println)
  }

  //格式化订单信息并写入
  def format_order_data(ss: SparkSession): Unit = {
    //随机生产100个用户
    val user_num = ArrayBuffer.empty[Int]
    while (user_num.size < 100) {
      val uid = Random.nextInt(200) + 1
      if (!user_num.contains(uid)) {
        user_num += uid
      }
    }

    //随机生产100个订单号
    val order_num = ArrayBuffer.empty[Int]
    while (order_num.size < 100) {
      val fid = Random.nextInt(899999) + 100000
      if (!order_num.contains(fid)) {
        order_num += fid
      }
    }

    //随机生成低价票、中价票、高价票的选择
    val ticket_choose = ArrayBuffer.empty[Int]
    while (ticket_choose.size < 100) {
      val tid = Random.nextInt(3)
      if ((tid == 0) || (tid == 2) || (tid == 4))
        ticket_choose += tid
    }

    val flight_data = ss.sparkContext.textFile("data/order_use_data.csv").map(_.split(","))
      .map(row => row(1) + ',' + row(2) + ',' //航班号,日期
        + get_token(row(9).split(' ')(1), row(10).split(' ')(1), 100, 1) + ',' //积分
        + row(11) + ',' + row(12) + ',' + row(14) + ',' + row(15) + ',' + row(17) + ',' + row(18)) //三种票价
      .take(100)

    val seat_level = Map(0 -> 'E', 1 -> 'S', 2 -> 'F')
    val order_data = ArrayBuffer[String]()
    for (index <- 0 to 99) {
      order_data += order_num(index).toString + ',' + flight_data(index).split(",")(0) + ',' + //订单号,航班号
        flight_data(index).split(",")(1) + ',' + seat_level(Random.nextInt(3)) + ',' + user_num(index) + ',' + //日期,座位等级,会员卡号
        Random.nextInt(4).toString + ',' + flight_data(index).split(",")(2) + ',' + //退票/改签标志位,积分
        flight_data(index).split(",")(3 + ticket_choose(index)) + ',' + flight_data(index).split(",")(4 + ticket_choose(index))
    }
    val order_rdd = ss.sparkContext.parallelize(order_data.toList)
    order_rdd.saveAsTextFile("output/order_manage")
    //order_rdd.foreach(println)
  }

  //格式化增值服务信息并写入
  def format_service_data(ss: SparkSession): Unit = {
    val baggage_data = Map(0 -> "未办理额外行李托运", 1 -> "已办理额外行李托运")
    val insurance_data = Map(0 -> "未选择任何保险业务", 1 -> "航空意外险", 2 -> "航空责任保险", 3 -> "航班延误险",
      4 -> "航空意外险/航空责任保险", 5 -> "航空意外险/航班延误险", 6 -> "航空责任保险/航班延误险", 7 -> "航空意外险/航班延误险/航班延误险")
    val food_data = Map(0 -> "未订购旅行餐饮", 1 -> "已订购旅行餐饮")

    val add_service_data = ss.sparkContext.textFile("data/order_number_data.csv").map(row => row.toString + ',' +
      baggage_data(Random.nextInt(2)) + ',' + insurance_data(Random.nextInt(8)) + ',' +
      food_data(Random.nextInt(2)))

    add_service_data.saveAsTextFile("output/add_service_data")
    //add_service_data.foreach(println)
  }

  //格式化航班兑换信息并写入
  def format_flight_exchange_data(ss: SparkSession): Unit = {
    val flight_exchange_info = ss.sparkContext.textFile("data/flight_data.csv").map(_.split(","))
      .map(row => row(0) + ',' + get_token(row(4), row(5), 888, 1) + ',' + row(2) + ',' + row(3) + ',' + row(1) + ' ' + row(4) + ','
        + get_arr_date(row(4), row(5), row(1)) + ' ' + row(5))
    flight_exchange_info.saveAsTextFile("output/flight_exchange")
    //flight_exchange_info.foreach(println)
  }
}
