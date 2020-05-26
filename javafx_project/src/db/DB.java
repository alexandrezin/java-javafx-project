package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection connection = null;
	
	public static Connection openConnection() {
		try {
			if (connection == null) {
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				connection = DriverManager.getConnection(url, properties);
			}
		}
		catch(SQLException exception) {
			throw new DbException(exception.getMessage());
		}
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		}
		catch (SQLException exception) {
			throw new DbException(exception.getMessage());
		}
	}
	
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		}
		catch (IOException exception) {
			throw new DbException(exception.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} 
			catch (SQLException exception) {
				throw new DbException(exception.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} 
			catch (SQLException exception) {
				throw new DbException(exception.getMessage());
			}
		}
	}
}