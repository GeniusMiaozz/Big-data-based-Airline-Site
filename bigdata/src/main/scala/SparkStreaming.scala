import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

object SparkStreaming {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:/Program Files/hadoop-2.6.0/hadoop-2.6.0")
    val conf = new SparkConf().setMaster("local[2]").setAppName("t1");
    val sc = new StreamingContext(conf, Seconds(10));
    val dir = "data/log";
    sc.checkpoint(dir);
    val pairs = sc.socketTextStream("localhost", 9999)
      .flatMap(_.split("\\s+"))
      .map(x => (x, 1));

    var wc = pairs.reduceByKey(_+_);
    wc.print();

    sc.start();
    sc.awaitTermination();
  }
}
