# Spark Cassandra Scala
Spark + Cassandra + Scala experiments.


## Dependencies
- Running in Spark local mode, operates on *nix words file /usr/share/dict/words.
- Tested on Scala 2.13.1, Spark 3.5.2, Spark Cassandra Connector 3.5.1, and Scalactic and ScalaTest 3.2.19. Please see SparkCassandra/build.sbt for the list of library dependencies.

## Configuration
```
Spark:
spark/conf/spark-env.sh edits:
export SPARK_LOCAL_IP=127.0.0.1
export SPARK_MASTER_IP=127.0.0.1
```
```
Cassandra:
from cqlsh:
CREATE KEYSPACE test WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
CREATE TABLE test.words(word text PRIMARY KEY, count int);
```
## Usage
From the sbt console:
```
> test
```
![results](https://cloud.githubusercontent.com/assets/7256774/13377851/d5003c4e-ddb2-11e5-8c72-2c2aa3c93bfb.png)
