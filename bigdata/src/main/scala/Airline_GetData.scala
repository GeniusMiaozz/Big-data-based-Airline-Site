import lib.library
import func.format_func

object Airline_GetData {
  def main(args: Array[String]): Unit = {
    //设置Hadoop路径0
    System.setProperty("hadoop.home.dir", "D:/Program Files/hadoop-2.6.0/hadoop-2.6.0")

    //获取接入点
    val ss = library.get_sparkSession()

    format_func.format_flight_data(ss)//格式化航班查询数据并写入
  }
}
