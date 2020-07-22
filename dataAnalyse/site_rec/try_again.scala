import com.neusoft.yu.MyRoot
import java.util.Properties
import java.io.FileInputStream
import java.sql.Connection
import java.sql.DriverManager



object try_again {
    case class site(rating:String,rank:String,name:String,locate:String,price:String)
  case class kmeans(MEMBER_NO:String,kmeansType:String)
  case class rec_result(kmeansType:String,province:String,counts:String)
  case class air_data(MEMBER_NO:String,WORK_PROVINCE:String,WORK_COUNTRY:String)//0,6,7
  case class p_a(Province:String,add:String)
  
  def main(args: Array[String]): Unit = {
    val spark= MyRoot.sparkSession()
     
     import spark.implicits._
     
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

    memberRecDF.show()
    memberRecDF.createTempView("t_memberRecDF")
    
     var province_rdd=spark.read.textFile("data/province_add.txt").map(_.split(" ")).map(arr=>p_a(arr(0),arr(1)))
     province_rdd.createTempView("t_province_add")
    
     var reprovince_add=spark.sql("select a.kmeansType, a.MEMBER_NO, a.WORK_PROVINCE, a.recProvince,b.add from t_memberRecDF a,t_province_add b where a.recProvince=b.Province")
     reprovince_add.createTempView("t_reprovince_add")
     
     var site_rdd=spark.read.textFile("data/site.txt").map(_.split(" ")).map(arr=>site(arr(0),arr(1),arr(2),arr(3),arr(4)))
     site_rdd.createTempView("t_site") 
     
     var sql="select a.kmeansType, a.MEMBER_NO, a.WORK_PROVINCE, a.recProvince,a.add,b.name,b.price,b.rating from t_reprovince_add a,t_site b where a.add=b.locate and b.rank in('1')"
     spark.sql(sql).show()
     
     var temp=spark.sql(sql).rdd
     System.out.println(temp.count())
     var row=temp.take(temp.count().toInt)
     
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
        
        val user_id=row(j).get(1).toString()
        val site_name=row(j).get(5).toString()
        val hot=row(j).get(7).toString()
        val address=row(j).get(3).toString()
        val price=row(j).get(6).toString()
        
        val query="insert into site_rec(userid,ATTRACTION,HOT_COUNT,LOCATION,price) "+
                                              "values("+"'"+user_id+"','"+site_name+"','"+hot+"','"+address+"','"+price+"')"           
        ps.addBatch(query)      
        ps.executeBatch()
      }
    } catch {
       case e => e.printStackTrace
     }     
     
  }
}