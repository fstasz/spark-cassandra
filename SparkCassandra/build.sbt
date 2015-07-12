name := "SparkCassandra"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.4.0"

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.3.0-M1"