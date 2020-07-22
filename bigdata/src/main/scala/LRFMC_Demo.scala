import lib.library
import func.analysis_func
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors

object LRFMC_Demo {

  //L:会员入会时间距观测窗口结束月数 9项:LOAD_TIME - 1项:FFP_DATE
  //R:客户最近一次乘坐公司飞机距观测窗口结束的月数 22项:LAST_TO_END
  //F:客户在观测窗口内乘坐公司飞机的次数 10项:FLIGHT_COUNT
  //M:客户在观测窗口内累计的飞行里程 16项:SEG_KM_SUM
  //C:客户在观测窗口内乘坐舱所对应的折扣系数的平均值 28项:avg_discount

  def main(args: Array[String]): Unit = {
    //设置Hadoop路径0
    System.setProperty("hadoop.home.dir", "D:/Program Files/hadoop-2.6.0/hadoop-2.6.0")

    //获取接入点
    val ss = library.get_sparkSession()

    //获取元数据
    val air_data = analysis_func.get_airline_rdd(ss);

    //处理元数据为Vector集合的形式
    val air_vector = air_data.filter(row => row.length == 8)
      .map(row => (row(0).toString.toInt,
        Vectors.dense(analysis_func.get_month(row(1).toString, row(2).toString),
          row(3).toString.toDouble,
          row(4).toString.toDouble,
          row(5).toString.toDouble,
          row(6).toString.toDouble)))

    //获取元数据中用于分析的RDD
    val air_rdd = ss.sparkContext.parallelize(air_vector.map(item => item._2))

    //利用KMeans算法进行聚类
    val typenum = 5
    val iternum = 20
    val model = KMeans.train(air_rdd, typenum, iternum)

    //统计多个聚类中心的平均值
    val i = analysis_func.get_avg_vector(model.clusterCenters, typenum)
    //    for (index <- model.clusterCenters) {
    //      println(index.toString)
    //    }
    //    println("avg:" + i.toString)

    val value_type = analysis_func.judge_value_type(model.clusterCenters, i)
    //    value_type.foreach(println)

    //对每个用户分析其类别
    val predicts = air_vector.map(item => (item._1, value_type(model.predict(item._2))))
    predicts.foreach(println)

    //将信息写回数据库
    //write_info_to_mysql(ss, predicts)
  }
}
