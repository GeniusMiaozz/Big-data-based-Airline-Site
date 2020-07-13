package lib

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object library {
  //创建一个本地SparkContext接入点
  def get_SparkContext(): SparkContext = {
    val conf = new SparkConf().setMaster("local").setAppName("MySparkContext")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    sc
  }

  //创建一个本地SparkSession接入点
  def get_sparkSession(): SparkSession = {
    val ss = SparkSession.builder().master("local").appName("MySparkSession").getOrCreate()
    ss.sparkContext.setLogLevel("WARN")
    ss
  }
}
