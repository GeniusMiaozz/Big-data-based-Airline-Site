import com.neusoft.yu.MyRoot
import java.util.Properties
import java.io.FileInputStream
import java.sql.Connection
import java.sql.DriverManager


object hotel {
  case class kmeans(Member_no:String,Type:String)
  case class rec(Type:String,name:String,count:String)
  case class p_c(Province:String,City:String,add:String)
  case class hotel(hotel_name:String,price:String,locate:String,add:String,hot:Double)
  def main(args: Array[String]): Unit = {
     val ss= MyRoot.sparkSession()
     
     import ss.implicits._
     
     
     val kmeans_path="data/kmeans_result.txt"
     val rec_path="data/top5-rec_result.txt"
     
     var kmeans_rdd=ss.read.textFile(kmeans_path).map(_.split(",")).map(arr=>kmeans(arr(0),arr(1)))
     kmeans_rdd.createTempView("t_kmeans")
     var rec_rdd=ss.read.textFile(rec_path).map(_.split(" ")).map(arr=>rec(arr(0),arr(1),arr(2)))
     rec_rdd.createTempView("t_rec")
     
     var sql="select a.Member_no,a.Type,b.name,b.count from t_kmeans a,t_rec b where a.Type=b.Type"

     var kmeans_rec_rdd=ss.sql(sql)
     kmeans_rec_rdd.createTempView("t_kmeans_rec")//|Member_no|Type|name|count|
     /*
      * |    54993|   1|  河南|   41|
|    54993|   1|  湖南|   41|
|    54993|   1|  湖北|   44|
|    54993|   1| 广东省|   57|
|    54993|   1|  吉林|   70|
|    54993|   1|  上海|   88|
|    54993|   1|  新疆|  136|
|    54993|   1|  辽宁|  165|
|    54993|   1|  北京|  240|
      */
     
     val province_path="data/province_city_dest.txt"
     var province_rdd=ss.read.textFile(province_path).map(_.split(" ")).map(arr=>p_c(arr(0),arr(1),arr(2)))
     province_rdd.createTempView("t_province")
     
    // sql="select distinct a.Member_no, a.name,b.add from t_kmeans_rec a ,t_province b where a.name=b.Province"
               //ss.sql(sql).show()
     //var kmeans_rec_province=ss.sql(sql)
     //kmeans_rec_province.createTempView("t_kmeans_rec_province")//|Member_no|name|add|
     
     var hotel_rdd=ss.read.textFile("data/hotel.txt").map(_.split(" ")).map(arr=>hotel(arr(0),arr(1),arr(2),arr(3),arr(4).toDouble))
     hotel_rdd.createTempView("t_hotel")
     //hotel_name:String,price:String,locate:String,add:String,hot:String
    // sql="select a.Member_no,a.name,a.add,b.hotel_name,b.price,b.hot from t_kmeans_rec_province a,t_hotel b where a.add=b.add order by b.hot desc"
    sql="select distinct a.Member_no,a.name,b.hotel_name,b.price,b.hot from t_kmeans_rec a,t_hotel b where a.name=b.locate order by a.Member_no desc,b.hot desc"
    ss.sql(sql).show() 
 //   var hotel_result=ss.sql(sql)
     
     var temp=ss.sql(sql).rdd
     System.out.println(temp.count())
     var row=temp.take(temp.count().toInt)
     
        //  ss.sql(sql).show()
     
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
        //|Member_no|name|add|     hotel_name|price|hot|
        val user_id=row(j).get(0).toString()
        val HOTEL_name=row(j).get(2).toString()
        val hot=row(j).get(4).toString()
        val address=row(j).get(1).toString()
        val price=row(j).get(3).toString()
        
        val query="insert into hotel_rec(HOTEL,LOCATION,AVG_PRICE,COUNTER,userid) "+
                                              "values("+"'"+HOTEL_name+"','"+address+"','"+price+"','"+hot+"','"+user_id+"')"           
        ps.addBatch(query)      
        ps.executeBatch()
      }
    } catch {
       case e => e.printStackTrace
     }     
     
     
     
     
     //select a.* from tb a where 2 > (select count(*) from tb where name = a.name and val > a.val ) order by a.name,a.val
   //  sql="select * from result a group by a.Member_no order by a.hot desc"
    //sql="select * from result a where 2>(select count(*) from result where Member_no=a.Member_no and hot>a.hot) order by a.hot,a.val"
     
     
  }
}