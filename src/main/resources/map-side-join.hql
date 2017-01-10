create temporary function get_numeric_ip as 'com.dmittov.mrdemo.udf.GetNumericIP' using jar '${hiveconf:udfPath}';

-- Count hits by country.
-- The alternative way is to count unique ip addressed and then join country names,
-- but I don't use Tez and it might be less effective due to extra disk operations.
create table country_report_${hiveconf:event_date} as
select get_country(remotehost) as country,
       count(*) as cnt
from apachelog
where event_date = ${hiveconf:event_date}
group by get_country(remotehost)
order by cnt desc;
