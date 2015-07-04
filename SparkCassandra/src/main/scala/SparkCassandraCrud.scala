import org.apache.spark.{ SparkConf, SparkContext }
import com.datastax.spark.connector._

object SparkCassandraCrud {
  val AppName = "CassandraSpark"
  val SparkMaster = "spark://localhost:7077"
  val CassandraConnection = "spark.cassandra.connection.host"
  val CassandraNode = "localhost"
  val CassandraKeyspace = "test"
  val CassandraTable = "words"
  val SearchString = "spark"
  val WordFile = "/usr/share/dict/words"
  val WordColumn = "word"
  val CountColumn = "count"

  case class Words(word: String, count: Int)

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf(true)
      .set(CassandraConnection, CassandraNode)
      .setAppName(AppName)
      .setMaster(SparkMaster)
    val sc = new SparkContext("local[2]", AppName, conf)
    val wordList = sc.textFile(WordFile, 2).cache()
    val sparkWords = wordList.filter(line => line.contains(SearchString)) // return words containing SearchString

    persistWords(sparkWords.toArray())
    
    def persistWords(words: Array[String]) = {
      for (i <- 1 until words.length) {
        val collection = sc.parallelize(Seq(Words(words(i), i)))
        collection.saveToCassandra(CassandraKeyspace, CassandraTable, SomeColumns(WordColumn, CountColumn))
      }
    }
  }
}