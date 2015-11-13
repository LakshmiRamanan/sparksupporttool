package com.sparksupporttool.beans;

import java.util.List;

public class ResultBean {

	String query;
	List<String> columnNames;
	List<Object> rows;
	String exception;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		return "ResultBean [query=" + query + ", columnNames=" + columnNames
				+ ", rows=" + rows + ", exception=" + exception + "]";
	}

}
