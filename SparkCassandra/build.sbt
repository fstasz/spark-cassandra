name := "SparkCassandraCrud"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.3.1"

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.3.0-M1"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.2.0"