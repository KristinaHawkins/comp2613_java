/**
 * Project: FirstAssignment
 * File: FileDataWriter.java
 * Date: Feb 21, 2017
 * Time: 4:12:07 PM
 */
package a00998715.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.ApplicationException;
import a00998715.data.Customer;
import a00998715.data.Inventory;
import a00998715.data.Service;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class FileDataWriter {

	private static final Logger LOG = LogManager.getLogger(FileDataWriter.class);

	public static void writeCustomersReport(ArrayList<Customer> listOfCustomers) throws ApplicationException {

		String HORIZONTAL_LINE = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------";
		String HEADER_FORMAT = " %2s %-6s %-15s %-15s %-25s %-15s %-15s %-15s %-25s %-15s\n";
		String CUSTOMER_FORMAT = "%3s %06d %-15s %-15s %-25s %-15s %-15s %-15s %-25s %-15s\n";

		PrintWriter outputStream = null;
		File sourceFile = new File("customers_report.txt");

		try {
			outputStream = new PrintWriter(new FileWriter(sourceFile));

			outputStream.println("Customers Report");
			outputStream.println(HORIZONTAL_LINE);
			outputStream.format(HEADER_FORMAT, "#.", "ID", "First Name", "Last Name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date");
			outputStream.println(HORIZONTAL_LINE);

			int counter = 1;
			for (Customer i : listOfCustomers) {
				outputStream.format(CUSTOMER_FORMAT, counter++ + ".", i.getId(), i.getFirstName(), i.getLastName(), i.getStreetName(), i.getCity(),
						i.getPostalCode(), i.getPhoneNumber(), i.getEmail(), i.getDate());
			}

		} catch (IOException e) {
			throw new ApplicationException(e.getMessage());
		} finally {
			outputStream.close();
		}

		LOG.info("Successfully wrote customers report into file");
	}

	public static void writeInventory(ArrayList<Inventory> listOfInventory, boolean total) throws ApplicationException {

		String INVENTORY_HEADER = String.format("%-30s %-30s %-15s %-15s %-15s %s \n", "Make+Model", "Description", "Part#", "Price", "Quantity",
				(total) ? "Value" : "");
		String INVENTORY_FORMAT = "%-30s %-30s %-15s %-15.2f %-15d" + ((total) ? "%,.2f" : "") + "\n";
		String HORIZONTAL_LINE = "--------------------------------------------------------------------------------------------------------";

		PrintWriter outputStream = null;
		File sourceFile = new File("inventory_report.txt");

		try {
			outputStream = new PrintWriter(new FileWriter(sourceFile));

			double totalAmount = 0;

			outputStream.println("Inventory Report");
			outputStream.println(HORIZONTAL_LINE);
			outputStream.format(INVENTORY_HEADER, "#.", "ID", "First Name", "Last Name", "Street", "City", "Postal Code", "Phone", "Email",
					"Join Date");
			outputStream.println(HORIZONTAL_LINE);

			for (Inventory i : listOfInventory) {
				outputStream.format(INVENTORY_FORMAT, i.getMotorcycleId(), i.getDescription(), i.getPartNumber(), i.getPrice(), i.getQuantity(),
						total ? i.getPrice() * i.getQuantity() : "");
				totalAmount += i.getPrice() * i.getQuantity();
			}

			if (total) {
				outputStream.format("%s $%,.2f", "Value of current inventory: ", totalAmount);
			}

		} catch (IOException e) {
			throw new ApplicationException(e.getMessage());
		} finally {
			outputStream.close();
		}

		LOG.info("Successfully wrote inventory report into file");
	}

	public static void writeServiceReport(ArrayList<Service> listOfService) throws ApplicationException {

		String SERVICE_HEADER = "%-15s %-15s %-20s %-15s %-15s %-15s \n";
		String SERVICE_FORMAT = "%-15s %-15s %-20s %-15s %-15d %-15.3f \n";
		String HORIZONTAL_LINE = "--------------------------------------------------------------------------------------------------------";

		PrintWriter outputStream = null;
		File sourceFile = new File("service_report.txt");

		try {
			outputStream = new PrintWriter(new FileWriter(sourceFile));

			outputStream.println("Service Report");
			outputStream.println(HORIZONTAL_LINE);
			outputStream.format(SERVICE_HEADER, "First Name", "Last Name", "Make", "Model", "Year", "Mileage");
			outputStream.println(HORIZONTAL_LINE);

			for (Service i : listOfService) {
				outputStream.format(SERVICE_FORMAT, i.getFirstName(), i.getLastName(), i.getMake(), i.getModel(), i.getYear(), i.getMileage());
			}

		} catch (IOException e) {
			throw new ApplicationException(e.getMessage());
		} finally {
			outputStream.close();
		}

		LOG.info("Successfully wrote service report into file");

	}

}
