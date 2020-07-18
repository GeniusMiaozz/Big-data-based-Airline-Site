import com.neusoft.yu.MyRoot
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.feature.StandardScaler
import org.apache.spark.mllib.clustering.KMeans


object air_kmeans {
  //客户编号 性别 年龄 平均折扣率 总精英积分 积分兑换次数 总累计积分 总基本积分
 // case class User(MEMBER_NO:String,GENDER:String,AGE:String,avg_discount:String,EP_SUM_YR_1:String, EXCHANGE_COUNT:String, EP_SUM:String, Points_Sum:String, BP_SUM:String)//case定义一个样本类
  def main(args: Array[String]): Unit = {
    val ss= MyRoot.sparkSession()
     
     import ss.implicits._
    //1.导入数据   file-->rdd
     
     val air_path="data/air_data.txt";

    var air_rdd=ss.sparkContext.textFile(air_path).map(_.split(",")).filter(arr=>arr.length==44)
    .map(arr=>({
      var GENDER=(if(arr(3)=='男')"1" else "2").toDouble
      var AGE=(if(arr(8).isEmpty()) "0" else arr(8)).toDouble
      var avg_discount=(if(arr(28).isEmpty()) "0" else arr(28)).toDouble
      var EP_SUM_YR_1=(if(arr(12).isEmpty()) "0" else arr(12)).toDouble
      var EXCHANGE_COUNT=(if(arr(27).isEmpty()) "0" else arr(27)).toDouble
      var EP_SUM=(if(arr(33).isEmpty()) "0" else arr(33)).toDouble
      var Points_Sum=(if(arr(37).isEmpty()) "0" else arr(37)).toDouble
      var BP_SUM=(if(arr(11).isEmpty()) "0" else arr(11)).toDouble
      Vectors.dense(Array(GENDER,AGE,avg_discount,EP_SUM_YR_1,EXCHANGE_COUNT,EP_SUM,Points_Sum,BP_SUM))
    },arr(0))) //var MEMBER_NO=arr(0)
    
    //var air_df=air_rdd.toDF()
   // air_rdd.take(1).foreach(println)

    /*
    //模型训练
    var iter=20
    var models=Seq(2,3,4,5,6).map{k=>
      var model=KMeans.train(vector, k, iter)
      var wscc=model.computeCost(vector)+680*scala.math.log(k)/scala.math.log(150)//惩罚函数
      (k,iter,model,wscc)
    }
    models.foreach(t=>println("cluster:"+t._1+" wscc:"+t._4))
    var sort_model=models.sortBy(_._4)
    println("最优:"+"cluster:"+sort_model(0)._1+" wscc:"+sort_model(0)._4)
    */
    
    var opt_model = KMeans.train(air_rdd.map(_._1),4,30)
    
    System.out.println("聚类标号和聚类中心点")
    var ClusCenAndTag = opt_model.clusterCenters.map(v=>(opt_model.predict(v),v))//预测所属聚类
    ClusCenAndTag.foreach{
      case(tag,center)=>println("标号:" + tag + " 聚类中心:" + center)
    }
     
     
     air_rdd.map(v=>(v._2,opt_model.predict(v._1))).foreach(t=>println(t._1+","+t._2))
    
    /*
    System.out.println("各类乘客数")
    air_rdd.
    //var standard_rdd = new StandardScaler(true,true).fit(air_rdd.map(_._1)).transform(air_rdd)
    //air_df.createTempView("users")
    air_rdd.toDF().foreach(r=>{
      var content = r(0)
      for(i <- 1 to r.length-1 ){
        var temp = r.getString(i)
        content = content + " " + i + ":" +  temp
      }
      println(content)
    })
    /*
     * MEMBER_NO:String,
     * GENDER:String,
     * AGE:String,
     * avg_discount:String,
     * EP_SUM_YR_1:String, 
     * EXCHANGE_COUNT:String, 
     * EP_SUM:String, 
     * Points_Sum:String, 
     * BP_SUM:String
     */
*/
  }
}