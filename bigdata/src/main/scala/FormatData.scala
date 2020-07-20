import lib.library
import func.format_func

object FormatData {
  def main(args: Array[String]): Unit = {
    //设置Hadoop路径
    System.setProperty("hadoop.home.dir", "D:/Program Files/hadoop-2.6.0/hadoop-2.6.0")

    //获取接入点
    val ss = library.get_sparkSession()

    //format_func.format_flight_data(ss) //格式化航班查询数据并写入
    //    format_func.format_people_data(ss)//格式化会员及管理员的注册信息并写入
    //    format_func.format_member_info_data(ss) //格式化会员个人信息数据并写入
    //    format_func.format_order_data(ss)//格式化订单信息并写入
    //format_func.format_service_data(ss) //格式化增值服务信息并写入
    format_func.format_flight_exchange_data(ss) //格式化航班兑换信息并写入
  }
}
