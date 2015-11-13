package com.sparksupporttool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sparksupporttool.beans.QueryBean;
import com.sparksupporttool.beans.ResultBean;
import com.sparksupporttool.beans.ResultJsonBean;
import com.sparksupporttool.bo.CassandraSparkBO;

@Component
public class CassandraSparkService {

	@Autowired
	CassandraSparkBO cassandraSparkBO;

	public ResultBean getAccountBalanceByRealmId(String realmId) {
		return cassandraSparkBO.getAccountBalanceByRealmId(realmId);
	}

	public ResultBean getQueryResults(QueryBean query) {
		return cassandraSparkBO.getQueryResults(query);
	}

	public ResultJsonBean getQueryResultsAsJson(QueryBean query) {
		return cassandraSparkBO.getQueryResultsAsJson(query);
	}

}
