/**
 * Project: Assignment2
 * File: DataReader.java
 * Date: Mar 21, 2017
 * Time: 9:43:04 PM
 */
package a00998715.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.ApplicationException;
import a00998715.Bcmc;
import a00998715.data.inventory.Inventory;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class DataReader {
	private static final Logger LOG = LogManager.getLogger(Bcmc.class);

	private static final String INVENTORY_FILE_NAME = "inventory.dat";
	public static final String FIELD_DELIMITER = "\\|";

	private static ArrayList<String> readFile(String fileName) throws ApplicationException, IOException {
		LOG.debug("DataReader read file");

		ArrayList<String> listOfLines = new ArrayList<>();

		File file = new File(fileName);
		if (!file.exists()) {
			throw new ApplicationException("File " + fileName + " doesn't exists");
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine(); // skip first line
			while ((line = br.readLine()) != null) {
				listOfLines.add(line);
			}
		} catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		} finally {
			br.close();
		}

		return listOfLines;
	}

	public static ArrayList<Inventory> createInventoryList() throws ApplicationException, IOException {
		LOG.debug("DataReader create Inventory list");

		ArrayList<Inventory> result = new ArrayList<>();
		ArrayList<String> listOfLines = readFile(INVENTORY_FILE_NAME);

		for (String s : listOfLines) {
			String[] itemInformation = s.split(FIELD_DELIMITER);

			result.add(new Inventory.Builder(itemInformation[0]).description(itemInformation[1]).partNumber(itemInformation[2])
					.price(itemInformation[3]).quantity(itemInformation[4]).build());
		}

		return result;
	}

}
