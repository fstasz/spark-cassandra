# Spark Cassandra
Spark + Cassandra experiments.


## Dependencies
Running local mode on OS X, operates on Unix words file for spark/cassandra operations.

## Configuration
```
Spark:
spark-env.sh edits:
export SPARK_LOCAL_IP=127.0.0.1
export SPARK_MASTER_IP=127.0.0.1
```
```
Cassandra:
from cqlsh:
CREATE KEYSPACE test WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
CREATE TABLE test.words(word text PRIMARY KEY, count int);
```
