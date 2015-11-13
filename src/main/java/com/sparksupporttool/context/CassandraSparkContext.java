package com.sparksupporttool.context;

import javax.annotation.PostConstruct;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.cassandra.CassandraSQLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.bdp.spark.DseSparkContext;

@Component
public class CassandraSparkContext {

	private SparkConf conf;
	private SparkContext sc;
	private CassandraSQLContext csc;

	@Value("${spark.master}")
	private String master;

	@Value("${cassandra.host}")
	private String hostName;

	@Value("${cassandra.default.keyspace}")
	private String keyspace;

	@PostConstruct
	public void init() {
		conf = new SparkConf(true).setMaster(master)
				.setAppName("org.sparkexample.SparkCassandra")
				.set("spark.cassandra.connection.host", hostName);
		sc = DseSparkContext.apply(conf);
		csc = new CassandraSQLContext(sc);
		if (null != keyspace) {
			csc.setKeyspace(keyspace);
		}

	}

	public CassandraSQLContext getCsqlContext() {
		return csc;
	}

	public void stopSpark() {
		sc.stop();
	}
}
