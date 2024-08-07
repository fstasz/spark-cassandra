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

import org.scalatest.funsuite.AnyFunSuite
import SparkCassandra._
import com.datastax.spark.connector._

class SparkCassandraSuite extends AnyFunSuite with TestUtil {

  test("Words containing spark in Unix words file should persist to Cassandra") {

      persistToCassandra()
  }

  test("Filtered words persisted to Cassandra should contain spark") {

    val expectedSparkiness =
      sc.cassandraTable("test", "words").select("word").where("word = ?", "sparkiness")
    val row = expectedSparkiness.first()
    assert(row.getString(0) === "sparkiness")
  }

  test("Total number of persisted words should equal 30") {

    val wordCount = SparkCassandra.sc.cassandraTable("test", "words").count()
    assert(wordCount === 30)
  }
}