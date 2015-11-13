package com.sparksupporttool.bo.impl;

import org.apache.spark.sql.cassandra.CassandraSQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sparksupporttool.beans.QueryBean;
import com.sparksupporttool.beans.ResultBean;
import com.sparksupporttool.beans.ResultJsonBean;
import com.sparksupporttool.bo.CassandraSparkBO;
import com.sparksupporttool.context.CassandraSparkContext;
import com.sparksupporttool.util.QueryUtil;

@Component
public class CassandraSparkBOImpl implements CassandraSparkBO {

	@Autowired
	CassandraSparkContext cassandraSparkContext;

	public ResultBean getAccountBalanceByRealmId(String realmId) {
		CassandraSQLContext csc = cassandraSparkContext.getCsqlContext();
		String query = "SELECT allocated_balance from authorizations a join accounts ac on a.esign_account_id = ac.esign_account_id where ac.realm_or_userid= '"
				+ realmId + "'";
		ResultBean result = QueryUtil.executeQuery(csc, query);
		return result;
	}

	@Override
	public ResultBean getQueryResults(QueryBean query) {
		CassandraSQLContext csc = cassandraSparkContext.getCsqlContext();
		ResultBean result = QueryUtil.executeQuery(csc, query.getQuery());
		return result;
	}

	@Override
	public ResultJsonBean getQueryResultsAsJson(QueryBean query) {
		CassandraSQLContext csc = cassandraSparkContext.getCsqlContext();
		ResultJsonBean result = QueryUtil.getQueryResultsAsJson(csc,
				query.getQuery());
		return result;
	}

}