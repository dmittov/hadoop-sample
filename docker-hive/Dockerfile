FROM sequenceiq/hadoop-docker:2.7.1
MAINTAINER mittov@gmail.com

ENV PATH $PATH:$HADOOP_PREFIX/bin

RUN curl -s http://apache.mirror.anlx.net/hive/hive-1.2.1/apache-hive-1.2.1-bin.tar.gz  | tar -xz -C /usr/local
# ADD provision/apache-hive-1.2.1-bin /usr/local/hive
RUN ln -s /usr/local/apache-hive-1.2.1-bin /usr/local/hive
ENV HIVE_HOME /usr/local/hive
ENV PATH $PATH:$HIVE_HOME/bin

ADD provision/start-hive.sh /etc/start-hive.sh
ADD provision/start-hadoop.sh /etc/start-hadoop.sh
ADD provision/bootstrap.sh /etc/bootstrap.sh

RUN chown root:root /etc/start-*.sh && chmod 700 /etc/start-*.sh
RUN chown root:root /etc/bootstrap.sh && chmod 700 /etc/bootstrap.sh

RUN schematool -dbType derby -initSchema -verbose

RUN curl -o /tmp/hive-contrib-1.2.1.jar \
    http://central.maven.org/maven2/org/apache/hive/hive-contrib/1.2.1/hive-contrib-1.2.1.jar
# ADD provision/hive-contrib-1.2.1.jar /tmp/hive-contrib-1.2.1.jar

ADD provision/access.log.2016-10-07 /tmp/access.log.2016-10-07
ADD provision/mrdemo-udf-1.0-SNAPSHOT.jar /tmp/mrdemo-udf-1.0-SNAPSHOT.jar
ADD provision/IP2LOCATION-LITE-DB1.CSV /tmp/IP2LOCATION-LITE-DB1.CSV

CMD ["/etc/bootstrap.sh", "-bash"]

# Hive jdbc port
EXPOSE 10000
# Hdfs ports
EXPOSE 50010 50020 50070 50075 50090
# Mapred ports
EXPOSE 19888
# Yarn ports
EXPOSE 8030 8031 8032 8033 8040 8042 8088
# Other ports
EXPOSE 49707 2122

# docker run --rm -p 10000:10000 -v /Users/mittov/Documents/hadoop:/opt/data -it hive
