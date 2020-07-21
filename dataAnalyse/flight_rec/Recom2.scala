import com.neusoft.yu.MyRoot
import org.apache.spark.sql.SparkSession
import java.util.logging.Logger
import java.util.logging.Level
import java.util.Properties
import java.io.FileInputStream
import java.sql.Connection
import java.sql.DriverManager


object Recom2 {
  case class flight(flightComName:String,flightId:String,flightDate:String,fromCity:String,fromPlantName:String,toCity:String,toPlantName:String,t1:String,t2:String,date1:String,date2:String,s1:String,s2:String,s3:String,s4:String,s5:String,s6:String,s7:String,s8:String,s9:String)
  case class p_c(province:String,city:String)
  case class kmeans(MEMBER_NO:String,kmeansType:String)
  case class rec_result(kmeansType:String,province:String,counts:String)
  case class air_data(MEMBER_NO:String,WORK_PROVINCE:String,WORK_COUNTRY:String)//0,6,7
    def main(args: Array[String]): Unit = {
  val spark= MyRoot.sparkSession()
     
  import spark.implicits._

    //读取航班信息 主要用于关联航班出发和目的地的城市省份

    val flightDF = spark.read.textFile("data/flight_query_data_out.csv").map(_.split(",")).map(arr=>flight(arr(0),arr(1),arr(2),arr(3),arr(4),arr(5),arr(6),arr(7),arr(8),arr(9),arr(10),arr(11),arr(12),arr(13),arr(14),arr(15),arr(16),arr(17),arr(18),arr(19)))
    flightDF.createTempView("t_flightDF")

    val provinceCityDF = spark.read.textFile("data/province_city.csv").map(_.split(",")).map(arr=>p_c(arr(0),arr(1)))
    provinceCityDF.createTempView("t_provinceCityDF")

    provinceCityDF.show(100)

    //航班对应的省份城市
    val flightProvinceDF = spark.sql("select distinct a.flightComName, a.flightId, a.fromCity, b.province as fromProvince, a.toCity, c.province as toProvince ,a.flightDate,a.s1,a.s2 from t_flightDF a left join t_provinceCityDF b on a.fromCity = b.city left join t_provinceCityDF c on a.toCity = c.city " +
      "where b.province is not null and c.province is not null")

    flightProvinceDF.show(10)
    flightProvinceDF.createTempView("t_flightProvinceDF")

    //读取用户类别信息 以及类别推荐结果信息
    val recResultDF = spark.read.textFile("data/rec_result.csv").map(_.split(",")).map(arr=>rec_result(arr(0),arr(1),arr(2)))
    recResultDF.createTempView("t_recResultDF")
    
    val kmeansDF = spark.read.textFile("data/kmeans_result.csv").map(_.split(",")).map(arr=>kmeans(arr(0),arr(1)))
    kmeansDF.createTempView("t_kmeansDF")

    val csvDataDF = spark.read.textFile("data/airdata.csv").map(_.split(",")).map(arr=>air_data(arr(0),arr(6),arr(7)))
    csvDataDF.createTempView("t_airdata")

    //获取客户的工作地点
    val memberDF = spark.sql("select a.MEMBER_NO, a.kmeansType, b.WORK_PROVINCE from t_kmeansDF a , t_airdata b where a.MEMBER_NO = b.MEMBER_NO and b.WORK_COUNTRY = 'CN' ")
    memberDF.createTempView("t_memberDF")

    //关联数据信息 并排除用户所在的省份
    val memberRecDF = spark.sql("select a.kmeansType, a.MEMBER_NO, a.WORK_PROVINCE, b.province as recProvince from t_memberDF a, t_recResultDF b where a.kmeansType = b.kmeansType and a.WORK_PROVINCE != b.province order by a.MEMBER_NO desc")

   // memberRecDF.show()
    memberRecDF.createTempView("t_memberRecDF")

    //推荐的航班数据结果信息
    val resultDF = spark.sql("select * from t_memberRecDF a, t_flightProvinceDF b where a.WORK_PROVINCE = b.fromProvince and a.recProvince = b.toProvince order by a.MEMBER_NO desc")
    resultDF.show()
    resultDF.createTempView("t_final")
    
    System.out.println("总记录数："+resultDF.count())
    //val sql = spark.sql("select a.*,b.date,b.price,b.discount from t_final a,t_flightDF b where t_final.flightId=t_flightDF.flightId")
    //System.out.println("this way")
   // sql.show()
    //格式转换
    val temp = resultDF.rdd
    val row=temp.take(resultDF.count().toInt)
   //row.foreach(t=>println(t.get(0)))
  // println(row(0).get(1))
    //写入数据库
    var p=new Properties()
    var in=new FileInputStream("src/db.properties")
    p.load(in)
    val driver =p.getProperty("driver")
    val url = p.getProperty("url")
    val username =p.getProperty("user")
    val password = p.getProperty("password")
    
    var connection:Connection = null
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val ps = connection.createStatement()
    
    try {           
      for (j <- 0 to (temp.count().toInt-1)){ 
        val user_type=row(j).get(0).toString()
        val user_id=row(j).get(1).toString()
        val user_workprovince=row(j).get(2).toString()
        val user_rec_province=row(j).get(3).toString()
        val flight_company=row(j).get(4).toString()
        val flight_no=row(j).get(5).toString()
        val from_city=row(j).get(6).toString()
        val from_province=row(j).get(7).toString()
        val to_city=row(j).get(8).toString()
        val to_province=row(j).get(9).toString()
        val date=row(j).get(10).toString()
        val price=row(j).get(11).toString()
        val discount=row(j).get(12).toString()
        val query="insert into flight_recommendation_table(kmeans_type,user_id,user_workprovince,user_rec_province,flight_company,flight_no,from_city,from_province,to_city,to_province,date,price,discount) "+
                                              "values("+"'"+user_type+"','"+user_id+"','"+user_workprovince+"','"+user_rec_province+"','"+flight_company+"','"+flight_no+"','"+from_city+"','"+from_province+"','"+to_city+"','"+to_province+"','"+date+"','"+price+"','"+discount+"')"           
        ps.addBatch(query)      
        ps.executeBatch()
      }
    } catch {
       case e => e.printStackTrace
     }     
       
    spark.stop()
  }
}