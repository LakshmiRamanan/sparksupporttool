# sparksupporttool

#CQLSH ++


SPARK SUPPORT TOOL

Introduction: 

This tool is aimed at making the querying of Cassandra DB simpler. This uses DSE's CassandraSparkContext, which leverages spark's map-reduce on a cluster, to query like you query a relational database ( i.e, using joins and qcross multiple keyspaces) 

There is a CLI version and also a REST API. 

How to use? 

For CLI version : 
1. Clone the code from git
2. Import the maven project on an IDE, run the CassandraSparkCli.java
3. You can start typing the query on the terminal

For the REST version: 
1. Install the Advanced Rest Client plugin on the browswe
2. Clone the code from git
3. mvn clean install
4. Deploy the sparksupporttool.war on a tomcat 

Testing using Advanced Rest Client:
URL: http://192.168.59.103/:8080/sparksupporttool/query/

JSON PAYLOAD:
{
"query":"select * from reminder where app_name='test'"
}

Output will be a JSON. 

NOTE: 
The host name of the cassandra database should be provided in cscontext.properties.In the same file, By default, spark master is 'local'. In case you know a host where spark's master node runs, please change it to 'spark://hostname:port'. Usually, spark master runs on spark://127.0.0.1:7077 (local). 
You may or maynot provide cassandra keyspace. Please be careful while querying on multiple keyspace. You must provide keyspace.table if no keyspace is set.

ENVIRONMENTAL CONFIGURATIONS:
Recommended re/jdk : 1.7
Recommended tomcat : Tomcat 7

TO-DO:
1. Proper logging
2. Docker setup clean up
3. Web UI plugin
4. Check on update
5. Check on spark's architecture to talk to cassandra
