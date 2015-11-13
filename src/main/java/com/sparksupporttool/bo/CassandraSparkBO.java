package com.sparksupporttool.bo;

import com.sparksupporttool.beans.QueryBean;
import com.sparksupporttool.beans.ResultBean;
import com.sparksupporttool.beans.ResultJsonBean;

public interface CassandraSparkBO {

	ResultBean getAccountBalanceByRealmId(String realmId);

	ResultBean getQueryResults(QueryBean query);

	ResultJsonBean getQueryResultsAsJson(QueryBean query);

}