/**
 * Project: Assignment2
 * File: InventoryDao.java
 * Date: Mar 18, 2017
 * Time: 11:02:57 PM
 */
package a00998715.data.inventory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.dao.Dao;
import a00998715.data.Database;
import a00998715.data.DbConstants;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class InventoryDao extends Dao {
	private static final Logger LOG = LogManager.getLogger();

	public static final String TABLE_NAME = DbConstants.INVENTORY_TABLE_NAME;
	private Database database;
	private static InventoryDao instance;

	private InventoryDao() throws FileNotFoundException, IOException {
		super(TABLE_NAME);
	}

	public static InventoryDao getInstance() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new InventoryDao();

		return instance;
	}

	@Override
	public void create() throws SQLException {
		String sql = String.format(
				"create table %s(" // 1
						+ "%s VARCHAR(40), " // 2
						+ "%s VARCHAR(30), " // 3
						+ "%s VARCHAR(30), " // 4
						+ "%s VARCHAR(10), " // 5
						+ "%s VARCHAR(10)) ", // 6
				TABLE_NAME, // 1
				Fields.MOTORCYCLE_ID.getName(), // 2
				Fields.DESCRIPTION.getName(), // 3
				Fields.PART_NUMBER.getName(), // 4
				Fields.PRICE.getName(), // 5
				Fields.QUANTITY.getName()); // 6
		LOG.debug(sql);
		super.executeUpdate(sql);
	}

	public void add(Inventory item) throws SQLException {

		try {
			String sql = String.format(
					"insert into %s values(" // 1 Motorcycle id
							+ "'%s', " // 2 Motorcycle id
							+ "'%s', " // 3 Description
							+ "'%s', " // 4 Part Number
							+ "'%s', " // 5 Price
							+ "'%s')", // 6 Quantity
					tableName, // 1
					item.getMotorcycleId(), // 2 Motorcycle id
					item.getDescription(), // 3 Description
					item.getPartNumber(), // 4 Part Number
					item.getPrice(), // 5 Price
					item.getQuantity()); // 6 Quantity
			LOG.debug(sql);
			super.executeUpdate(sql);
		} finally {
			// close(statement);
		}
	}

	public void update(Inventory item) throws SQLException {
		try {
			String sql = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'", TABLE_NAME, // 1
					Fields.MOTORCYCLE_ID.getName(), item.getMotorcycleId(), // 2
					Fields.DESCRIPTION.getName(), item.getDescription(), // 3
					Fields.PART_NUMBER.getName(), item.getPartNumber(), // 4
					Fields.PRICE.getName(), item.getPrice(), // 5
					Fields.QUANTITY.getName(), item.getQuantity(), // 6
					Fields.MOTORCYCLE_ID.getName(), item.getMotorcycleId()); // 7
			LOG.debug(sql);
			super.executeUpdate(sql);
		} finally {
			// close(statement);
		}
	}

	public void delete(Inventory item) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.MOTORCYCLE_ID.getName(), item.getMotorcycleId());
			LOG.debug(sql);
			super.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	public ArrayList<Inventory> getInventoryList() throws SQLException {

		ArrayList<Inventory> result = new ArrayList<>();
		String sql = String.format("SELECT * FROM %s", TABLE_NAME);
		ResultSet resultSet = super.executeSelect(sql);
		while (resultSet.next()) {
			result.add(new Inventory.Builder(resultSet.getString(Fields.MOTORCYCLE_ID.getName())) //
					.description(resultSet.getString(Fields.DESCRIPTION.getName())) //
					.partNumber(resultSet.getString(Fields.PART_NUMBER.getName())) //
					.price(resultSet.getString(Fields.PRICE.getName())) //
					.quantity(resultSet.getString(Fields.QUANTITY.getName())) //
					.build()); //
		}
		close(resultSet.getStatement());
		return result;
	}

	public void dropTable() throws Exception {
		super.drop();
	}

	public enum Fields {

		MOTORCYCLE_ID("motorcycle_id", "VARCHAR", 40, 1), //
		DESCRIPTION("description", "VARCHAR", 30, 2), //
		PART_NUMBER("part_number", "VARCHAR", 30, 3), //
		PRICE("price", "VARCHAR", 10, 4), //
		QUANTITY("quantiry", "VARCHAR", 10, 5); //

		private final String name;
		private final String type;
		private final int length;
		private final int column;

		Fields(String name, String type, int length, int column) {
			this.name = name;
			this.type = type;
			this.length = length;
			this.column = column;
		}

		public String getType() {
			return type;
		}

		public String getName() {
			return name;
		}

		public int getLength() {
			return length;
		}

		public int getColumn() {
			return column;
		}
	}

}
