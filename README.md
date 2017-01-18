# hadoop-sample

The application parses apache log file, maps the hits by ip with countries and produces coutry hit report.

Subprojects:
* App: client application, receives a signal (spring-integration for example, it's out of scope), initiates hadoop pipeline computations and sends notifications when jobs are done (out of scope).
* UDF: UDF hive functions. Spring framework.

### App
Simple Spring-Hive/Spring-Batch application with xml config. It's quite archaic, but it's just a demo and I used different types of spring configurations.
The main idea is: Hive is almost always better than direct MR. Map-Reduce requires to persist results after each stage. With hive pipeline I can write less code and have fine controll over MR operations. In the extreme case, when I do not trust hive optimizer, I can even use a particular Hive query for every MR operation. So at least it has the same performance. But the optimizer and Tez can boost operations performance without any coding. And the SQL-like pipeline is flexible.
The trouble is that hive is limited and complex things are done with Pig or direct MR. I can fix it with Hive User Defined Functions.

With spring-batch I can easily replace particular hive jobs and job steps and even replace them with Spark jobs.

### UDF
So, UDFs become the core part of the project. And we need the contemporary tools to develop them. So, I used IoC in UDF subproject. May be using lightweight Guice would be more suitable, but I use Spring in the App subproject, so it's ok to use it in UDF. The main headache is the Classpath. Hadoop clusters are large, there are lots of different projects using the same cluster and it would be a bad idea to spoil the common Classpath, so UDF deploys as uber jar.
