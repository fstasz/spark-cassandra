name := "SparkCassandra"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.0"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.0.1"

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "2.3.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"