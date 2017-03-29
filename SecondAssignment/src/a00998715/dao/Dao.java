/**
 * Project: A00123456Lab8
 * File: Dao.java
 */

package a00998715.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.Bcmc;
import a00998715.data.Database;

/**
 * @author Sam Cirka, A00123456
 *
 */
public abstract class Dao {
	private static final Logger LOG = LogManager.getLogger(Bcmc.class);
	protected final Database database;
	protected final String tableName;

	protected Dao(String tableName) throws FileNotFoundException, IOException {
		database = Database.getInstance();
		this.tableName = tableName;
	}

	public abstract void create() throws SQLException;

	/**
	 * @return the database
	 */
	public Database getDatabase() {
		return database;
	}

	/**
	 * Delete the database table
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void drop() throws SQLException, FileNotFoundException, IOException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			if (Database.tableExists(tableName)) {
				LOG.debug("drop table " + tableName);
				statement.executeUpdate("drop table " + tableName);
			}
		} finally {
			close(statement);
		}
	}

	/**
	 * Tell the database we're shutting down.
	 */
	public void shutdown() {
		database.shutdown();
		LOG.debug("database shutdown");
	}

	/**
	 * Executes the given SQL statement, that doesn't return anything. Such as INSERT, UPDATE, DELETE and CREATE
	 * 
	 * @param sqlStatement
	 * @throws SQLException
	 */
	protected void executeUpdate(String sqlStatement) throws SQLException {
		LOG.debug(sqlStatement);
		PreparedStatement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.prepareStatement(sqlStatement);
			statement.executeUpdate();
		} finally {
			close(statement);
		}
	}

	/**
	 * Execute INSERT statement and return ResultSet
	 * 
	 * @param sqlStatement
	 * @return
	 * @throws SQLException
	 */
	protected ResultSet executeSelect(String sqlStatement) throws SQLException {
		LOG.debug(sqlStatement);
		PreparedStatement statement = null;
		ResultSet resultSet;
		try {
			Connection connection = database.getConnection();
			statement = connection.prepareStatement(sqlStatement);
			resultSet = statement.executeQuery();
		} finally {
			// close(statement);
		}

		return resultSet;
	}

	protected void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			LOG.error("Failed to close statment" + e);
		}
	}

	public int getRowCount(Statement statement) throws SQLException {
		int count = -1;
		ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + tableName);
		while (resultSet.next()) {
			count = resultSet.getInt(1);
		}

		return count;
	}
}
