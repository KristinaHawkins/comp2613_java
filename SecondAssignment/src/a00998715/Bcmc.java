/**
 * Project: SecondAssignment
 * File: Bcmc.java
 * Date: Mar 23, 2017
 * Time: 11:40:23 AM
 */
package a00998715;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00998715.data.Database;
import a00998715.data.inventory.Inventory;
import a00998715.data.inventory.InventoryDao;
import a00998715.io.DataReader;
import a00998715.ui.MainFrame;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Bcmc {

	public static final Instant startTime = Instant.now();
	private static final String DROP_OPTION = "-drop";

	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";

	static {
		configureLogging();
	}

	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);

		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Instant startTime = Instant.now();
		LOG.info("Start: " + startTime);

		try {
			if (args.length == 1 && args[0].equalsIgnoreCase(DROP_OPTION)) {
				InventoryDao.getInstance().drop();
			}
			Bcmc bcmc = new Bcmc();
			bcmc.init();
			bcmc.createUI();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
	}

	private void createUI() {
		try {
			LOG.debug("Setting the Look & Feel");
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}

			MainFrame frame = new MainFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
	}

	private void init() {
		try {
			ArrayList<Inventory> inventoryItemsList = DataReader.createInventoryList();
			InventoryDao invDao = InventoryDao.getInstance();
			Database.getInstance();

			if (!Database.tableExists(InventoryDao.TABLE_NAME)) {
				invDao.create();

				for (Inventory i : inventoryItemsList) {
					invDao.add(i);
				}
				LOG.info("Inventory table filled with items");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
	}

}
