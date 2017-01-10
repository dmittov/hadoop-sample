add jar ${hiveconf:contribPath};

create temporary function get_country as 'com.dmittov.mrdemo.udf.GetCountryByIP' using jar '${hiveconf:udfPath}';

create external table geobase (
    lowerbound string,
    upperbound string,
    code string,
    country string)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
   "separatorChar" = ',',
   "quoteChar"     = '"'
)
stored as textfile
location '/user/root/input/geobase';

-- HQL doesn't support inequality joins, I can't use "remotehost between lowerbound and upperbound" condition
-- The solution is to clarify match for every row.
-- Geobase contents ~140k rows, daylog may content bilion rows (76k in example), therefore this way is desperate.
create table country_report_${hiveconf:event_date} as
select country,
       count(*) cnt
from (
select g.country,
       case
         when get_numeric_ip(log.remotehost) between cast(g.lowerbound as bigint) and cast(g.upperbound as bigint)
         then 1
         else 0
       end match
from apachelog log,
     geobase g
) t where t.match = 1
group by country
order by cnt desc;
