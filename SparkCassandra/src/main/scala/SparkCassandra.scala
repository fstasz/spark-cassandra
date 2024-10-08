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

import com.datastax.spark.connector._

/** Spark + Cassandra connector operations. */
object SparkCassandra extends SparkCassandraConfig {

  case class WordCount(word: String, count: Int)

  def persistToCassandra() = {
    /** Define RDD from words file */
    val wordList = sc.textFile(conf.getString("testConfig.wordsFile"), conf.getInt("sparkConfig.minPartitions")).cache()
    /** Return a new RDD of words containing SearchString */
    val sparkWords = wordList.filter(line => line.contains(conf.getString("cassandraConfig.searchString")))
    /** Save to Cassandra */
    def persist(words: Array[String]) = {
      for (i <- 1 until words.length) {
        val collection = sc.parallelize(Seq(WordCount(words(i), i)))
        collection.saveToCassandra(
          conf.getString("cassandraConfig.keyspace"), conf.getString("cassandraConfig.table"), SomeColumns(
            conf.getString("cassandraConfig.wordColumn"), conf.getString("cassandraConfig.countColumn")))
      }
    }
    
    persist(sparkWords.collect())
  }
}