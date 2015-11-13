package com.sparksupporttool.beans;

import java.util.List;
import java.util.Map;

public class ResultJsonBean {

	String query;
	List<Map<String, Object>> result;
	String exception;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<Map<String, Object>> getResult() {
		return result;
	}

	public void setResult(List<Map<String, Object>> result) {
		this.result = result;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		return "ResultJsonBean [query=" + query + ", result=" + result
				+ ", exception=" + exception + "]";
	}

}
