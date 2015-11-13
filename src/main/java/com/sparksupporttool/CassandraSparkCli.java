package com.sparksupporttool;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.sql.cassandra.CassandraSQLContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sparksupporttool.beans.ResultBean;
import com.sparksupporttool.context.CassandraSparkContext;
import com.sparksupporttool.util.QueryUtil;
import com.sparksupporttool.util.ResultFormatUtil;

public class CassandraSparkCli {
	public static void main(String[] args) {
		System.out.println("Hey there! \nWelcome to Cassandra-Spark-CLI.\n");
		CassandraSQLContext csc = null;

		try {
			Scanner scanner = new Scanner(System.in);
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:applicationContext.xml");
			CassandraSparkContext ctx = (CassandraSparkContext) context
					.getBean("cassandraSparkContext");
			csc = ctx.getCsqlContext();
			System.out
					.println("You can start querying now and when you are done, please key-in exit");
			System.out.print(">");
			boolean continueExecution = true;
			while (continueExecution) {
				String query = scanner.nextLine();

				if (!StringUtils.isBlank(query)) {
					if ("exit".equalsIgnoreCase(query)) {
						scanner.close();
						continueExecution = false;
					} else {
						ResultBean result = QueryUtil.executeQuery(csc, query);
						ResultFormatUtil.displayResult(result);
					}
				}
			}
			// CassandraSparkSingleton.stopSpark();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
