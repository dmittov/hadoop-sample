#!/bin/bash

/etc/start-hadoop.sh -d

# Wait for leaving the safe mode
hdfs dfsadmin -safemode leave
chmod +x /tmp/hive-contrib-1.2.1.jar
chmod +x /tmp/mrdemo-udf-1.0-SNAPSHOT.jar
hdfs dfs -put /tmp/hive-contrib-1.2.1.jar /user/root/input/hive-contrib.jar
hdfs dfs -put /tmp/mrdemo-udf-1.0-SNAPSHOT.jar /user/root/input/mrdemo-udf.jar
hdfs dfs -mkdir -p /user/root/apachelog/20161007
hdfs dfs -put /tmp/access.log.2016-10-07 /user/root/apachelog/20161007
hdfs dfs -mkdir /user/root/input/geobase
hdfs dfs -put /tmp/IP2LOCATION-LITE-DB1.CSV /user/root/input/geobase/geobase.csv

/etc/start-hive.sh

if [[ $1 == "-d" ]]; then
  while true; do sleep 1000; done
fi

if [[ $1 == "-bash" ]]; then
  /bin/bash
fi
