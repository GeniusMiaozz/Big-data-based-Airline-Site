import com.neusoft.yu.MyRoot


object my_rec {
  case class Airdata(MEMBER_NO:String,WORK_PROVINCE:String,WORK_COUNTRY:String)//WORK_PROVINC,MEMBER_NO
  case class Myk(MEMBER_NO:String,kmeansType:String)//MEMBER_NO,kmeansType
  def main(args: Array[String]): Unit = {
  
   val ss= MyRoot.sparkSession()
     
     import ss.implicits._
     
    val airdata_path="data/airdata.csv"
    val mykmeans_path="data/my-k-means.txt"

   //val encoding = "utf-8"
     var airdata_df=ss.read.textFile(airdata_path).map(_.split(",")).map(arr=>Airdata(arr(0),if(arr(6).isEmpty()) "北京" else arr(6),if(arr(7).isEmpty()) "CN" else arr(7)))
    airdata_df.createTempView("t_airdata")    
    var mykmeans_df=ss.read.textFile(mykmeans_path).map(_.split(",")).map(arr=>Myk(arr(0),arr(1)))
    mykmeans_df.createTempView("t_kmeansdata")
    
    var i = 0
    for(i <- 0 to 3){
      //每组推荐的结果信息
     var sql="select b.kmeansType, a.WORK_PROVINCE, count(*) as counts  from t_airdata a, t_kmeansdata b where b.kmeansType = " + i + "  and a.MEMBER_NO = b.MEMBER_NO and a.WORK_PROVINCE is not null and a.WORK_COUNTRY = 'CN' group by b.kmeansType, a.WORK_PROVINCE order by counts desc  "
      println("-------------"+i+"----------------")
      ss.sql(sql).show(10)
    }
   
   
  }
}