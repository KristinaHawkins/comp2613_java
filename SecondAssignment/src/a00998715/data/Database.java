/**
 * Project: examples8
 * File: Database.java
 * Date: 2012-10-28
 * Time: 12:26:04 PM
 */

package a00998715.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.Bcmc;

/**
 * @author scirka
 * 
 */
public class Database {

	public static final String DB_DRIVER_KEY = "db.driver";
	public static final String DB_URL_KEY = "db.url";
	public static final String DB_USER_KEY = "db.user";
	public static final String DB_PASSWORD_KEY = "db.password";

	private static final Logger LOG = LogManager.getLogger(Bcmc.class);

	private static Connection connection;
	private final Properties properties = new Properties();

	private static Database instance;

	private Database() throws FileNotFoundException, IOException {
		LOG.debug("Loading database properties from db.properties");
		LOG.debug("Database instantiated");
		properties.load(new FileInputStream(DbConstants.DB_PROPERTIES_FILENAME));
	}

	public static Database getInstance() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new Database();
		return instance;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return connection;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(properties.getProperty(DB_DRIVER_KEY));
		LOG.debug("Driver loaded");
		connection = DriverManager.getConnection(properties.getProperty(DB_URL_KEY), properties.getProperty(DB_USER_KEY),
				properties.getProperty(DB_PASSWORD_KEY));
		LOG.debug("Database connected");
	}

	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean tableExists(String targetTableName) throws SQLException {
		ResultSet resultSet = null;
		String tableName = null;
		try {
			DatabaseMetaData databaseMetaData = Database.getInstance().getConnection().getMetaData();

			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				tableName = resultSet.getString("TABLE_NAME");
				if (tableName.equalsIgnoreCase(targetTableName)) {
					LOG.debug("Found the target table named: " + targetTableName);
					return true;
				}
			}
		} catch (Exception e) {

		} finally {
			resultSet.close();
		}

		return false;
	}

}
