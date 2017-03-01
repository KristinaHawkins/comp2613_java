/**
 * Project: Lab4
 * File: Lab2.java
 * Date: Jan 22, 2017
 * Time: 3:18:33 PM
 */
package a00998715;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00998715.test.CustomerDaoTester;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Lab7 {

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
	private static final Logger LOG = LogManager.getLogger(Lab7.class);

	static void run(String[] args) throws FileNotFoundException, SQLException, IOException {

		try {
			new CustomerDaoTester(args).test();
		} catch (Exception e) {
			LOG.error(e.toString());
			System.exit(-1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Instant startTime = Instant.now();
		LOG.info(startTime);

		try {
			Lab7.run(args);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		} finally {

			Instant endTime = Instant.now();
			LOG.info(endTime);
			LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));

		}

	}

}
