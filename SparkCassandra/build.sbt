name := "SparkCassandra"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.0"

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.6.0-M1"

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"