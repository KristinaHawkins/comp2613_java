/**
 * Project: FirstAssignment
 * File: Bcmc.java
 * Date: Feb 9, 2017
 * Time: 2:04:33 PM
 */
package a00998715;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00998715.io.FileDataReader;
import a00998715.io.FileDataReader.CustomersReader;
import a00998715.io.Report;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Bcmc {

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	public static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger(Bcmc.class);

	/**
	 * @param args
	 * @throws ParseException
	 * @throws ApplicationException
	 */
	public static void main(String[] args) throws ApplicationException {

		Instant startTime = Instant.now();
		LOG.info(startTime);
		LOG.info("Program started");

		try {
			HashMap<String, String> optionsMap = commandLineArguments(args);
			if (args.length == 0) {
				optionsMap.put("service", "true");
				optionsMap.put("inventory", "true");
				optionsMap.put("customers", "true");
			}
			Go(optionsMap);
		} catch (Exception e) {
			LOG.error(e.getCause());
		}
		Instant endTime = Instant.now();
		LOG.info(endTime);
		LOG.info("Program finished");
		LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}

	public static void Go(HashMap<String, String> optionsMap) throws ApplicationException {

		if (optionsMap.get("customers") == "true") {
			new Report().printCustomers(CustomersReader.splitDataAndCreateCustomersList(), Boolean.valueOf(optionsMap.get("by_join_date")),
					Boolean.valueOf(optionsMap.get("descending")));
		}

		if (optionsMap.get("inventory") == "true") {
			new Report().printInventory(FileDataReader.InventoryReader.splitDataAndCreateInventoryList(), Boolean.valueOf(optionsMap.get("total")),
					Boolean.valueOf(optionsMap.get("by_count")), Boolean.valueOf(optionsMap.get("by_description")), optionsMap.get("make"),
					Boolean.valueOf(optionsMap.get("descending")));
		}

		if (optionsMap.get("service") == "true") {
			new Report().printService(FileDataReader.ServiceReader.createServiceList(), optionsMap.get("make"));
		}

	}

	public static HashMap<String, String> commandLineArguments(String[] args) throws ParseException {
		BcmcOptions.process(args);
		CommandLine commandLine = BcmcOptions.getCommandLine();
		Option[] options = commandLine.getOptions();

		HashMap<String, String> optionsMap = new HashMap<String, String>();
		optionsMap.put("service", "false");
		optionsMap.put("inventory", "false");
		optionsMap.put("customers", "false");
		optionsMap.put("total", "false");
		optionsMap.put("by_description", "false");
		optionsMap.put("by_count", "false");
		optionsMap.put("by_join_date", "false");
		optionsMap.put("make", "false");
		optionsMap.put("descending", "false");

		for (Option option : options) {
			if (commandLine.hasOption(option.getOpt())) {
				String value = commandLine.getOptionValue(option.getOpt());
				optionsMap.put(option.getLongOpt(), value == null ? "true" : value);
			} else {
				optionsMap.put(option.getLongOpt(), "false");
			}
		}

		return optionsMap;
	}

}
