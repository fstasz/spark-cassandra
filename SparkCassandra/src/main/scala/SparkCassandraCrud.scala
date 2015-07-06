/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
    val sc = new SparkContext(Configuration.ClusterUrl, Configuration.AppName, conf)
    
    /** Define RDD from words file */
    val wordList = sc.textFile(Configuration.WordsFile, 2).cache()
    
    /** Return a new RDD of words containing SearchString */
    val sparkWords = wordList.filter(line => line.contains(Configuration.SearchString))
    
    /** Save to Cassandra */
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
    val ClusterUrl = "local[2]"
    val CassandraConnection = "spark.cassandra.connection.host"
    val CassandraNode = "localhost"
    val CassandraKeyspace = "test"
    val CassandraTable = "words"
    val WordColumn = "word"
    val CountColumn = "count"
    val SearchString = "spark"
    val WordsFile = "/usr/share/dict/words"
  }
}