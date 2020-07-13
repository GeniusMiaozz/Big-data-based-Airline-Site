package func

import org.apache.spark.sql.SparkSession
import scala.util.Random

object format_func {

  //格式化航班查询数据并写入
  def format_flight_data(ss:SparkSession): Unit = {
    val flight_info = ss.sparkContext.textFile("data/flight_data.csv").map(_.split(","))
      .map(row => row(0) + ',' + row(1) + ','
        + row(2) + ',' + (Random.nextInt(2) + 1).toString + "号,"
        + row(3) + ',' + (Random.nextInt(2) + 1).toString + "号,"
        + "T" + (Random.nextInt(3) + 1).toString + ','
        + "T" + (Random.nextInt(3) + 1).toString + ','
        + row(1) + " " + row(4) + ','
        + row(1) + " " + row(5) + ','
        + (Random.nextInt(200) + (if (row(5).split(":")(0).toInt >= row(4).split(":")(0).toInt)
        row(5).split(":")(0).toInt - row(4).split(":")(0).toInt
      else row(5).split(":")(0).toInt + 24 - row(4).split(":")(0).toInt) * 200).toString + "." + Random.nextInt(100).toString
        + "," + Random.nextInt(250).toString)
    flight_info.saveAsTextFile("output/flight.csv")
  }


}
