/**
 * Project: FirstAssignment
 * File: FileReader.java
 * Date: Feb 12, 2017
 * Time: 10:31:26 AM
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
import a00998715.data.Customer;
import a00998715.data.Inventory;
import a00998715.data.Motorcycle;
import a00998715.data.Service;
import a00998715.data.util.Validator;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class FileDataReader {

	private static final Logger LOG = LogManager.getLogger(FileDataReader.class);

	public static final String FIELD_DELIMITER = "\\|";

	private static ArrayList<String> readFile(String fileName) throws ApplicationException {

		ArrayList<String> listOfFileLines = new ArrayList<>();

		File sourceFile = new File(fileName);
		if (!sourceFile.exists()) {
			throw new ApplicationException("File " + fileName + " not exist");
		}

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(sourceFile));
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				listOfFileLines.add(line);
			}
			br.close();
		} catch (IOException e) {
			throw new ApplicationException(e.getCause());
		}

		return listOfFileLines;
	}

	public static class CustomersReader {

		public static ArrayList<Customer> splitDataAndCreateCustomersList() throws ApplicationException {

			ArrayList<String> unFormattedArrayOfCustomers = readFile("customers.dat");
			ArrayList<Customer> customersList = new ArrayList<>();
			Customer newCustomer;

			for (int i = 0; i < unFormattedArrayOfCustomers.size(); i++) {
				String[] customerInformation = unFormattedArrayOfCustomers.get(i).split(FIELD_DELIMITER);
				Validator.isAppropriateNumberOfFieldsForRecord(customerInformation);

				newCustomer = new Customer.Builder(Integer.parseInt(customerInformation[0]), Validator.validatePhoneNumber(customerInformation[6]))
						.firstName(customerInformation[1]).lastName(customerInformation[2]).streetName(customerInformation[3])
						.city(customerInformation[4]).postalCode(Validator.validatePostalCode(customerInformation[5]))
						.email(Validator.validateEmail(customerInformation[7])).date(Validator.validateDateTime(customerInformation[8])).build();

				customersList.add(newCustomer);

			}

			LOG.info("Got successfully info from customers.dat file");
			return customersList;
		}
	}

	public static class MotorcycleReader {

		public static ArrayList<Motorcycle> splitDataAndCreateMotorcycleList() throws ApplicationException {

			ArrayList<String> unFormattedArrayOfMotorcycles = readFile("motorcycles.dat");
			ArrayList<Motorcycle> motorcyclesList = new ArrayList<>();
			Motorcycle newMotorcycle;

			for (int i = 0; i < unFormattedArrayOfMotorcycles.size(); i++) {
				String[] motorcycleInformation = unFormattedArrayOfMotorcycles.get(i).split(FIELD_DELIMITER);

				newMotorcycle = new Motorcycle.Builder(Integer.parseInt(motorcycleInformation[0]), motorcycleInformation[4])
						.make(motorcycleInformation[1]).model(motorcycleInformation[2]).year(Integer.parseInt(motorcycleInformation[3]))
						.mileage(Integer.parseInt(motorcycleInformation[5])).customerId(Integer.parseInt(motorcycleInformation[6])).build();

				motorcyclesList.add(newMotorcycle);

			}

			LOG.info("Got successfully info from motorcycles.dat file");
			return motorcyclesList;
		}

	}

	public static class InventoryReader {

		public static ArrayList<Inventory> splitDataAndCreateInventoryList() throws ApplicationException {

			ArrayList<String> unFormattedArrayOfInventory = readFile("inventory.dat");
			ArrayList<Inventory> inventoryList = new ArrayList<>();
			Inventory newInventory;

			for (int i = 0; i < unFormattedArrayOfInventory.size(); i++) {
				String[] inventoryInformation = unFormattedArrayOfInventory.get(i).split(FIELD_DELIMITER);

				newInventory = new Inventory.Builder(inventoryInformation[0]).description(inventoryInformation[1]).partNumber(inventoryInformation[2])
						.price(Double.parseDouble(inventoryInformation[3]) / 100).quantity(Integer.parseInt(inventoryInformation[4])).build();

				inventoryList.add(newInventory);

			}

			LOG.info("Got successfully info from inventory.dat file");
			return inventoryList;
		}

	}

	public static class ServiceReader {

		public static ArrayList<Service> createServiceList() throws ApplicationException {

			ArrayList<Service> serviceList = new ArrayList<>();
			ArrayList<Motorcycle> motorcyclesList = MotorcycleReader.splitDataAndCreateMotorcycleList();
			Customer tmpCustomerRecord;

			for (Motorcycle i : motorcyclesList) {
				Service tmpServiceRecord = new Service();
				tmpCustomerRecord = getCustomer(i.getCustomerId());
				tmpServiceRecord.setFirstName(tmpCustomerRecord.getFirstName());
				tmpServiceRecord.setLastName(tmpCustomerRecord.getLastName());
				tmpServiceRecord.setMake(i.getMake());
				tmpServiceRecord.setModel(i.getModel());
				tmpServiceRecord.setYear(i.getYear());
				tmpServiceRecord.setMileage(i.getMileage() / 1000);

				serviceList.add(tmpServiceRecord);
			}

			LOG.info("Created Service list");
			return serviceList;
		}

		private static Customer getCustomer(int customerId) throws ApplicationException {
			ArrayList<Customer> customersList = CustomersReader.splitDataAndCreateCustomersList();

			for (Customer i : customersList) {
				if (i.getId() == customerId)
					return i;
			}
			return new Customer.Builder(-1, "None").city("None").date("None").email("None").firstName("None").lastName("None").build();
		}

	}

}
