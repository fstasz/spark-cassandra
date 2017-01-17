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

trait SparkCassandraConfig {
  /** Configure SparkContext */
  val conf = new SparkConf(true)
    .set(Configuration.CassandraConnection, Configuration.CassandraNode)
    .setAppName(Configuration.AppName)
    .setMaster(Configuration.SparkMaster)

  /** Create SparkContext and register it as a singleton object */
  val sc = SparkContext.getOrCreate(conf)

  object Configuration {
    val AppName = "SparkCassandraScala"
    val CassandraConnection = "spark.cassandra.connection.host"
    val CassandraKeyspace = "test"
    val CassandraNode = "localhost"
    val CassandraTable = "words"
    val SparkMaster = "local[*]"
    val CountColumn = "count"
    val SearchString = "spark"
    val WordColumn = "word"
    val WordsFile = "/usr/share/dict/words"
  }
}