package com.quotes.database;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;

public class database_connection {


		private static Connection connection;

		public static Connection getConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quotesdb", "root", "rishant");
			} catch (Exception e) {
				Logger.getAnonymousLogger().log(Level.WARNING, e.toString());
			}
			return connection;
		}

}
