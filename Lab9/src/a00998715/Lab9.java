/**
 * Project: Lab9
 * File: Lab9.java
 * Date: Mar 9, 2017
 * Time: 10:19:07 PM
 */
package a00998715;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00998715.data.ApplicationException;
import a00998715.data.Customer;
import a00998715.database.CustomerDao;
import a00998715.database.Database;
import a00998715.database.DbConstants;
import a00998715.io.CustomerReader;
import a00998715.ui.MainFrame;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Lab9 {

	public static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger(Lab9.class);

	private Database database;
	private CustomerDao customerDao;
	private final Properties dbProperties;
	@SuppressWarnings("unused")
	private Connection connection;

	public Lab9() throws FileNotFoundException, IOException, SQLException {
		File dbPropertiesFile = new File(DbConstants.DB_PROPERTIES_FILENAME);
		dbProperties = new Properties();
		dbProperties.load(new FileReader(dbPropertiesFile));
		database = new Database(dbProperties);
		connection = database.getConnection();
		customerDao = new CustomerDao(database);
	}

	public void test() throws SQLException, ApplicationException {

		if (!Database.tableExists(CustomerDao.TABLE_NAME)) {
			customerDao.create();
			add(new CustomerReader().splitDataAndCreateCustomersList());
			LOG.info("Database doesn't exists. Created database.");
		}

		ArrayList<Customer> customersList = new CustomerDao(database).getCustomers();

		database.shutdown();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}

					LOG.info("Set Nimbus theme");

					MainFrame frame = new MainFrame(customersList);
					frame.setVisible(true);
					LOG.info("Main Frame created");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void add(ArrayList<Customer> customers) throws SQLException {

		for (Customer i : customers) {
			customerDao.add(i);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Lab9().test();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

}
