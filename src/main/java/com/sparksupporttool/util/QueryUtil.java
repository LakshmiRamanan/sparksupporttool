package com.sparksupporttool.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.cassandra.CassandraSQLContext;

import com.sparksupporttool.beans.ResultBean;
import com.sparksupporttool.beans.ResultJsonBean;

public class QueryUtil {

	public static ResultBean executeQuery(CassandraSQLContext csc, String query) {
		ResultBean resultBean = new ResultBean();
		if (query.endsWith(";")) {
			query = query.replaceAll(";", "");
		}
		resultBean.setQuery(query);
		List<Object> output = null;
		try {
			DataFrame data = csc.sql(query);

			resultBean.setColumnNames(extractColumnNamesFromData(data));

			output = data.toJavaRDD().map(new Function<Row, Object>() {
				public List<Object> call(Row row) {
					List<Object> tmp = new ArrayList<Object>();
					// System.out.println("row :" + row.get(0));
					for (int i = 0; i < row.length(); i++) {
						tmp.add(row.get(i));
					}
					return tmp;
				}

			}).collect();
		} catch (Exception e) {
			System.out.println("Message: " + e.getMessage());
			resultBean.setException(e.getMessage());
		}
		resultBean.setRows(output);
		return resultBean;
	}

	public static ResultJsonBean getQueryResultsAsJson(CassandraSQLContext csc,
			String query) {
		ResultJsonBean resultBean = new ResultJsonBean();
		if (query.endsWith(";")) {
			query = query.replaceAll(";", "");
		}
		resultBean.setQuery(query);
		List<Object> rows = null;
		try {
			DataFrame data = csc.sql(query);

			List<String> columnNames = extractColumnNamesFromData(data);

			rows = data.toJavaRDD().map(new Function<Row, Object>() {
				public List<Object> call(Row row) {
					List<Object> tmp = new ArrayList<Object>();
					for (int i = 0; i < row.length(); i++) {
						tmp.add(row.get(i));
					}
					return tmp;
				}

			}).collect();

			resultBean.setResult(ResultFormatUtil.formResultInBean(resultBean,
					columnNames, rows));

		} catch (Exception e) {
			System.out.println("Message: " + e.getMessage());
			resultBean.setException(e.getMessage());
		}
		return resultBean;
	}

	private static List<String> extractColumnNamesFromData(DataFrame data) {
		List<String> columnNames = new ArrayList<String>();
		String text = data.toString();
		String[] columns = text.split(", ");
		for (String column : columns) {
			String[] columnSplit = column.split(":");
			columnNames.add(columnSplit[0].replaceAll("\\[", ""));
		}
		return columnNames;
	}
}
