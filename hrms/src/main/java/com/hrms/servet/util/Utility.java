package com.hrms.servet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utility {
	private static String DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";
	private static String JDBC_URL = "jdbc:oracle:thin:@apex.oracle.com:1521:ppcpl_erp_modules/WKSP_BOLSTERSYS";
	private static String JDBC_USER_NAME = "bolstersys";
	private static String JDBC_PASSWORD = "123456";

	public static Connection getConnection() {

		Connection connection = null;
		try {
			Class.forName(DRIVER_CLASS_NAME);
			connection = DriverManager.getConnection(JDBC_URL, JDBC_USER_NAME, JDBC_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
