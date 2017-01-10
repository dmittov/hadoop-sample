add jar ${hiveconf:contribPath};

drop table if exists apachelog_${hiveconf:event_date};

create external table apachelog_${hiveconf:event_date} (
    remotehost string,
    remotelogname string,
    username string,
    time string,
    method string,
    uri string,
    proto string,
    status string,
    bytes string,
    referer string,
    useragent string)
row format serde 'org.apache.hadoop.hive.contrib.serde2.RegexSerDe'
with serdeproperties ("input.regex" = "^([^ ]*) +([^ ]*) +([^ ]*) +\\[([^]]*)\\] +\\\"([^ ]*) ([^ ]*) ([^ ]*)\\\" ([^ ]*) ([^ ]*) (?:\\\"-\\\")*\\\"(.*)\\\" (.*)$",
                      "output.format.string" = "%1$s %2$s %3$s %4$s %5$s %6$s %7$s %8$s %9$s %10$s %11$s"
                     )
stored as textfile
location '${hiveconf:rawLog}/${hiveconf:event_date}';

drop table if exists apachelog;

create table if not exists apachelog (
    remotehost string,
    remotelogname string,
    username string,
    time bigint,
    method string,
    uri string,
    proto string,
    bytes bigint,
    referer string,
    useragent string
    ) partitioned by (event_date string)
stored as parquet;

insert overwrite table apachelog partition (event_date='${hiveconf:event_date}')
select remotehost,
       remotelogname,
       username,
       unix_timestamp(time, 'dd/MMM/yyyy:hh:mm:ss Z') time,
       method,
       uri,
       proto,
       cast(bytes as bigint) bytes,
       referer,
       useragent
from apachelog_${hiveconf:event_date}
where status == '200';
