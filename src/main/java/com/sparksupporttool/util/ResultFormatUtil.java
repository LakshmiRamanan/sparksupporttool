package com.sparksupporttool.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sparksupporttool.beans.ResultBean;
import com.sparksupporttool.beans.ResultJsonBean;

public class ResultFormatUtil {

	public static void displayResult(ResultBean result) {
		System.out.println();
		if (result.getException() != null) {
			System.out.println(result.getException());
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(result.getQuery());
			formatAsTabSeparatedStrings(result.getColumnNames(), sb);
			List<Object> rows = result.getRows();

			for (Object row : rows) {
				formatAsTabSeparatedStrings(row, sb);
			}
			System.out.println(sb.toString());
			System.out.println(rows.size() + " rows returned\n");
		}
		System.out.print(">");

	}

	private static String formatAsTabSeparatedStrings(Object obj,
			StringBuilder sb) {

		sb.append("\n");
		if (obj instanceof List<?>) {
			List<Object> columns = (List<Object>) obj;
			for (int i = 0; i < columns.size(); i++) {
				sb.append(columns.get(i));
				if (i != columns.size() - 1) {
					sb.append("\t || \t");
				}

			}
		} else {
			String row = (String) obj;
			System.out.println("Row:" + row);
			sb.append(row.replaceAll(",", "\t || \t"));
		}
		return sb.toString();
	}

	public static List<Map<String, Object>> formResultInBean(
			ResultJsonBean resultBean, List<String> columnNames,
			List<Object> rows) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		for (Object row : rows) {
			Map<String, Object> result = new HashMap<String, Object>();
			List<Object> tmp = (List<Object>) row;
			for (int i = 0; i < columnNames.size(); i++) {
				result.put(columnNames.get(i), tmp.get(i));
			}
			results.add(result);
		}
		return results;
	}
}
