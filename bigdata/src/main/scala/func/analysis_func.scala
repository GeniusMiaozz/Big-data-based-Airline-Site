package func

import java.io.FileInputStream
import java.sql.DriverManager
import java.util.Properties

import org.apache.spark.mllib.linalg
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.{Row, SparkSession}

import scala.collection.mutable.Map

object analysis_func {
  //对于给定的两个日期(字符串),获取两日期的月数差。日期格式：yyyy-mm-dd
  def get_month(start_date: String, end_date: String): Double = {
    //获取起始、终止年份 以及两年对应的月份
    var start_year = start_date.split("-")(0)
    var end_year = end_date.split("-")(0)
    var start_month = start_date.split("-")(1)
    var end_month = end_date.split("-")(1)

    //计算月数差值
    if (start_year == end_year) {
      var result = end_month.toInt - start_month.toInt
      result.toDouble
    }
    else {
      var result = (end_year.toInt - start_year.toInt) * 12 + end_month.toInt + 12 - start_month.toInt
      result.toDouble
    }
  }

  //连接数据库,获取数据,格式化数据,返回元数据
  def get_airline_rdd(ss: SparkSession): Array[Row] = {
    val prop = new Properties()
    val parameter = new FileInputStream("configuration/db.properties")
    prop.load(parameter)
    val url = prop.getProperty("url")

    val air_data = ss.read.jdbc(url, "member_value_table", prop).collect()
    air_data
  }

  //计算多个中心点的平均中心点
  def get_avg_vector(center_arr: Array[linalg.Vector], typenum: Int): linalg.Vector = {
    val temp = Array(0.0, 0.0, 0.0, 0.0, 0.0)
    for (v <- center_arr) {
      temp(0) = BigDecimal(temp(0) + v(0)).setScale(14, BigDecimal.RoundingMode.HALF_UP).toDouble
      temp(1) = BigDecimal(temp(1) + v(1)).setScale(14, BigDecimal.RoundingMode.HALF_UP).toDouble
      temp(2) = BigDecimal(temp(2) + v(2)).setScale(14, BigDecimal.RoundingMode.HALF_UP).toDouble
      temp(3) = BigDecimal(temp(3) + v(3)).setScale(14, BigDecimal.RoundingMode.HALF_UP).toDouble
      temp(4) = BigDecimal(temp(4) + v(4)).setScale(14, BigDecimal.RoundingMode.HALF_UP).toDouble
    }
    temp(0) /= typenum
    temp(1) /= typenum
    temp(2) /= typenum
    temp(3) /= typenum
    temp(4) /= typenum
    val result = Vectors.dense(temp(0), temp(1), temp(2), temp(3), temp(4))
    result
  }

  //将分类后的信息写回
  def write_info_to_mysql(ss: SparkSession, predicts: Array[(Int, String)]): Unit = {
    val prop = new Properties()
    val parameter = new FileInputStream("configuration/db.properties")
    prop.load(parameter)
    val url = prop.getProperty("url")

    Class.forName("com.mysql.jdbc.Driver");
    val connect = DriverManager.getConnection(url, "root", "Miaozi329.");

    for (item <- predicts) {
      val sql = "update member_value_table set TYPE = '" + item._2 + "' where MEMBER_NO = " + item._1;
      val item_send = connect.prepareStatement(sql)
      item_send.executeUpdate()
    }
  }

  //对用户的不同类别判断价值信息
  def judge_value_type(center_arr: Array[linalg.Vector], avg_center: linalg.Vector): Map[Int, String] = {
    var note = Map[String, (Int, Double)]()
    var result = Map[Int, String]()

    for (index <- 0 to center_arr.length - 1) {
      //判断重要发展客户
      if ((center_arr(index)(1) < avg_center(1))
        && (center_arr(index)(2) < avg_center(2))
        && (center_arr(index)(3) < avg_center(3))) {
        result += (index -> "重要发展客户")
      }

      //判断重要保持客户及重要挽留客户
      else if ((center_arr(index)(1) < avg_center(1))
        && (center_arr(index)(2) > avg_center(2))
        && (center_arr(index)(3) > avg_center(3))
        && (center_arr(index)(4) > avg_center(4))) {
        val prop_R = (avg_center(1) - center_arr(index)(1)) / avg_center(1)
        val prop_F = (center_arr(index)(2) - avg_center(2)) / center_arr(index)(2)
        val prop_M = (center_arr(index)(3) - avg_center(3)) / center_arr(index)(3)
        val prop_C = (center_arr(index)(4) - avg_center(4)) / center_arr(index)(4)
        val prop_total = prop_R + prop_F + prop_M + prop_C
        if (!note.contains("max")) {
          //如果没有统计过任意一个 加入统计数据
          note += ("max" -> (index, prop_total))
        }
        else {
          //如果已有任意一个的统计记录 进行分类
          if (note("max")._2 > prop_total) {
            result += (note("max")._1 -> "重要保持客户")
            result += (index -> "重要挽留客户")
          }
          else {
            result += (note("max")._1 -> "重要挽留客户")
            result += (index -> "重要保持客户")
          }
        }
      }

      //判断低价值客户及一般价值客户
      else if ((center_arr(index)(1) > avg_center(1))
        && (center_arr(index)(2) < avg_center(2))
        && (center_arr(index)(3) < avg_center(3))
        && (center_arr(index)(4) < avg_center(4))) {
        val prop_R = (center_arr(index)(1) - avg_center(1)) / center_arr(index)(1)
        val prop_F = (avg_center(2) - center_arr(index)(2)) / avg_center(2)
        val prop_M = (avg_center(3) - center_arr(index)(3)) / avg_center(3)
        val prop_C = (avg_center(4) - center_arr(index)(4)) / avg_center(4)
        val prop_total = prop_R + prop_F + prop_M + prop_C
        if (!note.contains("min")) {
          ///如果没有统计过任意一个 加入统计数据
          note += ("min" -> (index, prop_total))
        }
        else {
          //如果已有任意一个的统计记录 进行分类
          if (note("min")._2 > prop_total) {
            result += (note("min")._1 -> "低价值客户")
            result += (index -> "一般价值客户")
          }
          else {
            result += (note("min")._1 -> "一般价值客户")
            result += (index -> "低价值客户")
          }
        }
      }
    }
    result
  }
}
