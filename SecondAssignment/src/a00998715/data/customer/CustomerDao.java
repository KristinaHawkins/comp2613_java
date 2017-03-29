/**
 * Project: Lab7
 * File: CustomerDao.java
 * Date: Feb 28, 2017
 * Time: 2:02:09 PM
 */
package a00998715.data.customer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.Bcmc;
import a00998715.dao.Dao;
import a00998715.data.DbConstants;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class CustomerDao extends Dao {

	public static final String TABLE_NAME = DbConstants.CUSTOMER_TABLE_NAME;
	private static CustomerDao instance;

	private static final Logger LOG = LogManager.getLogger(Bcmc.class);

	private CustomerDao() throws FileNotFoundException, IOException {
		super(TABLE_NAME);
	}

	public static CustomerDao getInstance() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new CustomerDao();

		return instance;
	}

	@Override
	public void create() throws SQLException {
		String sql = String.format(
				"create table %s(" // 1
						+ "%s VARCHAR(10), " // 2
						+ "%s VARCHAR(20), " // 3
						+ "%s VARCHAR(30), " // 4
						+ "%s VARCHAR(30), " // 5
						+ "%s VARCHAR(30), " // 6
						+ "%s VARCHAR(30), " // 7
						+ "%s VARCHAR(20), " // 8
						+ "%s VARCHAR(30), " // 9
						+ "%s VARCHAR(30), " // 10
						+ "primary key (%s) )", // 11
				tableName, // 1
				Fields.CUSTOMER_ID.getName(), // 2
				Fields.FIRST_NAME.getName(), // 3
				Fields.LAST_NAME.getName(), // 4
				Fields.STREET_NAME.getName(), // 5
				Fields.CITY.getName(), // 6
				Fields.POSTAL_CODE.getName(), // 7
				Fields.PHONE_NUMBER.getName(), // 8
				Fields.EMAIL.getName(), // 9
				Fields.DATE.getName(), // 10
				Fields.CUSTOMER_ID.getName()); // 11
		LOG.debug(sql);
		super.executeUpdate(sql);
	}

	public void add(Customer customer) throws SQLException {
		try {
			String sql = String.format(
					"insert into %s values(" // 1 tableName
							+ "'%s', " // 2 CustomerId
							+ "'%s', " // 3 FirstName
							+ "'%s', " // 4 LastName
							+ "'%s', " // 5 StreetName
							+ "'%s', " // 6 City
							+ "'%s', " // 7 PostalCode
							+ "'%s', " // 8 PhoneNumber
							+ "'%s', " // 9 Email
							+ "'%s')", // 10 Date
					tableName, // 1
					customer.getId(), // 2
					customer.getFirstName(), // 3
					customer.getLastName(), // 4
					customer.getStreetName(), // 5
					customer.getCity(), // 6
					customer.getPostalCode(), // 7
					customer.getPhoneNumber(), // 8
					customer.getEmail(), // 9
					customer.getDate()); // 10
			LOG.debug(sql);
			super.executeUpdate(sql);
		} finally {
		}
	}

	public Customer getCustomer(String customerId) throws SQLException, Exception {
		Customer customer = null;
		try {
			String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.CUSTOMER_ID.getName(), customerId);
			LOG.debug(sql);
			ResultSet resultSet = super.executeSelect(sql);

			// get the Student
			// throw an exception if we get more than one result
			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new Exception(String.format("Expected one result, got %d", count));
				}
				customer = new Customer.Builder(Integer.parseInt(resultSet.getString(Fields.CUSTOMER_ID.getName())),
						resultSet.getString(Fields.PHONE_NUMBER.getName())) //
								.firstName(resultSet.getString(Fields.FIRST_NAME.getName())) //
								.lastName(resultSet.getString(Fields.LAST_NAME.getName())) //
								.streetName(resultSet.getString(Fields.STREET_NAME.getName())) //
								.city(resultSet.getString(Fields.CITY.getName())) //
								.postalCode(resultSet.getString(Fields.POSTAL_CODE.getName())) //
								.email(resultSet.getString(Fields.EMAIL.getName())).date(resultSet.getString(Fields.DATE.getName()))//
								.build();
			}
		} finally {
		}

		return customer;
	}

	public void update(Customer customer) throws SQLException {
		try {
			String sql = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'",
					tableName, // 1
					Fields.CUSTOMER_ID.getName(), customer.getId(), // 2
					Fields.FIRST_NAME.getName(), customer.getFirstName(), // 3
					Fields.LAST_NAME.getName(), customer.getLastName(), // 4
					Fields.STREET_NAME.getName(), customer.getStreetName(), // 5
					Fields.CITY.getName(), customer.getCity(), // 6
					Fields.POSTAL_CODE.getName(), customer.getPostalCode(), // 7
					Fields.PHONE_NUMBER.getName(), customer.getPhoneNumber(), // 8
					Fields.EMAIL.getName(), customer.getEmail(), // 9
					Fields.CUSTOMER_ID.getName(), customer.getId()); // 10
			LOG.debug(sql);
			super.executeUpdate(sql);
		} finally {
		}
	}

	public void delete(Customer customer) throws SQLException {
		try {
			String sql = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.CUSTOMER_ID.getName(), String.valueOf(customer.getId()));
			LOG.debug(sql);
			super.executeUpdate(sql);
		} finally {
		}
	}

	public ArrayList<String> getIds() throws SQLException {
		ArrayList<String> customersIds = new ArrayList<>();

		try {
			String sql = String.format("SELECT customerId from %s", TABLE_NAME);
			ResultSet resultSet = super.executeSelect(sql);
			while (resultSet.next()) {
				customersIds.add(resultSet.getString(Fields.CUSTOMER_ID.getName()));
			}
		} finally {
		}
		return customersIds;
	}

	public ArrayList<Customer> getCustomers() throws SQLException {

		ArrayList<Customer> customers = new ArrayList<>();
		Customer customer;
		String sql = String.format("SELECT * from %s", TABLE_NAME);
		ResultSet resultSet = super.executeSelect(sql);
		try {
			while (resultSet.next()) {
				customer = new Customer.Builder(Integer.parseInt(resultSet.getString(Fields.CUSTOMER_ID.getName())),
						resultSet.getString(Fields.PHONE_NUMBER.getName())) //
								.firstName(resultSet.getString(Fields.FIRST_NAME.getName())) //
								.lastName(resultSet.getString(Fields.LAST_NAME.getName())) //
								.streetName(resultSet.getString(Fields.STREET_NAME.getName())) //
								.city(resultSet.getString(Fields.CITY.getName())) //
								.postalCode(resultSet.getString(Fields.POSTAL_CODE.getName())) //
								.email(resultSet.getString(Fields.EMAIL.getName())).date(resultSet.getString(Fields.DATE.getName()))//
								.build();
				customers.add(customer);
			}
		} finally {
			super.close(resultSet.getStatement());
		}
		return customers;

	}

	public enum Fields {

		CUSTOMER_ID("customerId", "VARCHAR", 10, 1), //
		FIRST_NAME("firstName", "VARCHAR", 20, 2), //
		LAST_NAME("lastName", "VARCHAR", 20, 3), //
		STREET_NAME("streetName", "VARCHAR", 30, 4), //
		CITY("city", "VARCHAR", 30, 5), //
		POSTAL_CODE("postalCode", "VARCHAR", 30, 6), //
		PHONE_NUMBER("phoneNUMBER", "VARCHAR", 10, 7), //
		EMAIL("email", "VARCHAR", 30, 8), //
		DATE("date", "DATE", -1, 9);

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
