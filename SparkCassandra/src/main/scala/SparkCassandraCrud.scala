import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._

object SparkCassandraCrud {

  case class WordCount(word: String, count: Int)

  def main(args: Array[String]): Unit = {
    /** Configure SparkContext */
    val conf = new SparkConf(true)
      .set(Configuration.CassandraConnection, Configuration.CassandraNode)
      .setAppName(Configuration.AppName)
      .setMaster(Configuration.SparkMaster)
      
    /** Create SparkContext */  
    val sc = new SparkContext(Configuration.ThreadConfig, Configuration.AppName, conf)
    
    /** Read text from words file */
    val wordList = sc.textFile(Configuration.WordFile, 2).cache()
    
    /** Return a new RDD of words containing SearchString */
    val sparkWords = wordList.filter(line => line.contains(Configuration.SearchString))
    
    /** Save words and count to Cassandra */
    def persist(words: Array[String]) = {
      for (i <- 1 until words.length) {
        val collection = sc.parallelize(Seq(WordCount(words(i), i)))
        collection.saveToCassandra(
          Configuration.CassandraKeyspace, Configuration.CassandraTable, SomeColumns(
            Configuration.WordColumn, Configuration.CountColumn))
      }
    }
    
    persist(sparkWords.toArray())
  }
  
  object Configuration {
    val AppName = "CassandraSpark"
    val SparkMaster = "spark://localhost:7077"
    val ThreadConfig = "local[2]"
    val CassandraConnection = "spark.cassandra.connection.host"
    val CassandraNode = "localhost"
    val CassandraKeyspace = "test"
    val CassandraTable = "words"
    val WordColumn = "word"
    val CountColumn = "count"
    val SearchString = "spark"
    val WordFile = "/usr/share/dict/words"
  }
}