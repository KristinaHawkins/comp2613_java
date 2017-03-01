/**
 * Project: Lab7
 * File: CustomerDaoTester.java
 * Date: Feb 28, 2017
 * Time: 4:26:08 PM
 */
package a00998715.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import a00998715.data.Customer;
import a00998715.database.CustomerDao;
import a00998715.database.Database;
import a00998715.database.DbConstants;
import a00998715.io.CustomerReader;
import a00998715.io.CustomerReport;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class CustomerDaoTester {

	private Database database;

	private CustomerDao customerDao;
	private final Properties dbProperties;
	@SuppressWarnings("unused")
	private Connection connection;

	public CustomerDaoTester(String[] args) throws FileNotFoundException, IOException, SQLException {

		File dbPropertiesFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
		dbProperties = new Properties();
		dbProperties.load(new FileReader(dbPropertiesFile));
		database = new Database(dbProperties);
		connection = database.getConnection();
		customerDao = new CustomerDao(database);

		if (args.length > 0 && args[0].equals("drop")) {
			customerDao.drop();
		}

	}

	public void test() throws Exception {

		if (!Database.tableExists(CustomerDao.TABLE_NAME)) {
			customerDao.create();
			add(new CustomerReader().splitDataAndCreateCustomersList());
		}

		print(customerDao.getIds());

		database.shutdown();
	}

	private void add(ArrayList<Customer> customers) throws SQLException {

		for (Customer i : customers) {
			customerDao.add(i);
		}

	}

	private void print(ArrayList<String> customersIds) throws SQLException, Exception {

		CustomerReport report = new CustomerReport();

		for (String i : customersIds) {
			report.addCustomer(customerDao.getCustomer(i));
		}

		report.print();

	}

}
