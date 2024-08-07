name := "SparkCassandra"

version := "1.0.0"

scalaVersion := "2.13.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.5.1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.1" % "provided"

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "3.5.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.19"

libraryDependencies += "org.scalatest" %% "scalatest-funsuite" % "3.2.19" % Test