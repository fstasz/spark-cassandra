name := "SparkCassandra"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0"

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.4.0-M1"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"