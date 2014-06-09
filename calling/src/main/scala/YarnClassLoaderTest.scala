import java.util.concurrent.TimeUnit
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.SparkContext

/**
 * Created by dbtsai on 6/9/14.
 */
object YarnClassLoaderTest {
  def main(args: Array[String]) {
    try {
      println("Creating a new spark context.")

      val sc = new SparkContext("yarn-standalone", "Yarn ClassLoader Test")
      val filePath = "hdfs://alpinenode2.alpinenow.local:8020/Users/dbtsai/libsvm/a9a"
      val rawRDD = sc.textFile(filePath)

      val result = rawRDD.map(x => Called.doubleString(x)).take(50)

      result.foreach(println)
    } catch {
      case th: Throwable => {
        th.getStackTraceString + th.getMessage
        TimeUnit.SECONDS.sleep(60)
      }
    }
    TimeUnit.SECONDS.sleep(60)
  }
}