
package com.airsupply.solr.adapter.test;

import org.apache.calcite.linq4j.function.Function1;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Unit test of the Calcite adapter for SOLR.
 */
public class SolrAdapterTest {
	private void close(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// ignore
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	public static String toLinux(String s) {
		return s.replaceAll("\r\n", "\n");
	}

	private void checkSql(String model, String sql) throws SQLException {
		checkSql(sql, model, output());
	}

	private Function1<ResultSet, Void> output() {
		return new Function1<ResultSet, Void>() {
			public Void apply(ResultSet resultSet) {
				try {
					output(resultSet, System.out);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				return null;
			}
		};
	}

	@SuppressWarnings("unused")
	private void checkSql(String model, String sql, final String... expected)
			throws SQLException {
		checkSql(sql, model, expect(expected));
	}

	/**
	 * Returns a function that checks the contents of a result set against an
	 * expected string.
	 */
	private static Function1<ResultSet, Void> expect(final String... expected) {
		return new Function1<ResultSet, Void>() {
			public Void apply(ResultSet resultSet) {
				try {
					final List<String> lines = new ArrayList<String>();
					SolrAdapterTest.collect(lines, resultSet);
					Assert.assertEquals(Arrays.asList(expected), lines);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				return null;
			}
		};
	}

	private void checkSql(String sql, String model,
			Function1<ResultSet, Void> fn) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
			Properties info = new Properties();
			info.put("model", jsonPath(model));
			connection = DriverManager.getConnection("jdbc:calcite:", info);
			statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(sql);
			fn.apply(resultSet);
		} finally {
			close(connection, statement);
		}
	}

	private String jsonPath(String model) {
		final URL url = SolrAdapterTest.class.getResource("/" + model + ".json");
		String s = url.toString();
		if (s.startsWith("file:")) {
			s = s.substring("file:".length());
		}
		return s;
	}

	private static void collect(List<String> result, ResultSet resultSet)
			throws SQLException {
		final StringBuilder buf = new StringBuilder();
		while (resultSet.next()) {
			buf.setLength(0);
			int n = resultSet.getMetaData().getColumnCount();
			String sep = "";
			for (int i = 1; i <= n; i++) {
				buf.append(sep)
						.append(resultSet.getMetaData().getColumnLabel(i))
						.append("=").append(resultSet.getString(i));
				sep = "; ";
			}
			result.add(toLinux(buf.toString()));
		}
	}

	private void output(ResultSet resultSet, PrintStream out)
			throws SQLException {
		final ResultSetMetaData metaData = resultSet.getMetaData();
		final int columnCount = metaData.getColumnCount();
		while (resultSet.next()) {
			for (int i = 1;; i++) {
				out.print(resultSet.getString(i));
				if (i < columnCount) {
					out.print(", ");
				} else {
					out.println();
					break;
				}
			}
		}
	}

	

	/**
	 * Reads from a table.
	 */
	@Test
	public void testSelect() throws SQLException {
//        String sql_employee="select * from EMPLOYEE where id>1 ";
//        System.out.println(sql_employee);
//		checkSql("solr_model", sql_employee);
//		
//		String sql_dept="select * from DEPTS ";
//        System.out.println(sql_dept);
//		checkSql("solr_model", sql_dept);
		
		String sql_cartesian="select * from DEPTS ,EMPLOYEE";
		 System.out.println(sql_cartesian);
			checkSql("solr_model", sql_cartesian);
	}
}

