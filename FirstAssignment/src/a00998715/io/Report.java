/**
 * Project: FirstAssignment
 * File: Report.java
 * Date: Feb 12, 2017
 * Time: 10:32:09 AM
 */
package a00998715.io;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.ApplicationException;
import a00998715.data.Customer;
import a00998715.data.Inventory;
import a00998715.data.Service;
import a00998715.data.util.CompareByCount;
import a00998715.data.util.CompareByDescription;
import a00998715.data.util.CompareByJoinedDate;
import a00998715.data.util.SortByMake;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Report {

	private static final Logger LOG = LogManager.getLogger(Report.class);

	private static final String HORIZONTAL_LINE = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------";
	private static final String HEADER_FORMAT = " %2s %-6s %-15s %-15s %-25s %-15s %-15s %-15s %-25s %-15s\n";
	private static final String CUSTOMER_FORMAT = "%3s %06d %-15s %-15s %-25s %-15s %-15s %-15s %-25s %-15s\n";
	private static final String SERVICE_HEADER = "%-15s %-15s %-20s %-15s %-15s %-15s \n";
	private static final String SERVICE_FORMAT = "%-15s %-15s %-20s %-15s %-15d %-15.3f \n";

	@SuppressWarnings("static-access")
	public void printCustomers(ArrayList<Customer> listOfCustomers, boolean by_join_date, boolean descending) throws ApplicationException {

		if (by_join_date) {
			if (descending) {
				listOfCustomers = CompareByJoinedDate.sortByJoinedDateDescending(listOfCustomers);
			} else {
				listOfCustomers = CompareByJoinedDate.sortByJoinedDate(listOfCustomers);
			}
		}

		System.out.println("Customers Report");
		System.out.println(HORIZONTAL_LINE);
		System.out.format(HEADER_FORMAT, "#.", "ID", "First Name", "Last Name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date");
		System.out.println(HORIZONTAL_LINE);

		int counter = 1;
		for (Customer i : listOfCustomers) {
			System.out.format(CUSTOMER_FORMAT, counter++ + ".", i.getId(), i.getFirstName(), i.getLastName(), i.getStreetName(), i.getCity(),
					i.getPostalCode(), i.getPhoneNumber(), i.getEmail(), i.getDate());
		}

		new FileDataWriter().writeCustomersReport(listOfCustomers);

		LOG.info("Printed customers");

	}

	@SuppressWarnings("static-access")
	public void printInventory(ArrayList<Inventory> listOfInventory, boolean total, boolean by_count, boolean by_description, String make,
			boolean descending) throws ApplicationException {

		if (by_count) {
			listOfInventory = (descending) ? CompareByCount.sortByCountDescending(listOfInventory) : CompareByCount.sortByCount(listOfInventory);
			LOG.info("By count");
		}

		if (by_description) {
			listOfInventory = (descending) ? CompareByDescription.sortByDescriptionDescending(listOfInventory)
					: CompareByDescription.sortByDescription(listOfInventory);
			LOG.info("By description");

		}

		if (make != "false") {
			listOfInventory = SortByMake.sortByMakeInventory(listOfInventory, make);
			LOG.info("By make " + make);
		}

		String INVENTORY_HEADER = String.format("%-30s %-30s %-15s %-15s %-15s %s \n", "Make+Model", "Description", "Part#", "Price", "Quantity",
				(total) ? "Value" : "");
		String INVENTORY_FORMAT = "%-30s %-30s %-15s %-15.2f %-15d" + ((total) ? "%,.2f" : "") + "\n";

		double totalAmount = 0;

		System.out.println("Inventory Report");
		System.out.println(HORIZONTAL_LINE);
		System.out.format(INVENTORY_HEADER, "#.", "ID", "First Name", "Last Name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date");
		System.out.println(HORIZONTAL_LINE);

		for (Inventory i : listOfInventory) {
			System.out.format(INVENTORY_FORMAT, i.getMotorcycleId(), i.getDescription(), i.getPartNumber(), i.getPrice(), i.getQuantity(),
					total ? i.getPrice() * i.getQuantity() : "");
			totalAmount += i.getPrice() * i.getQuantity();
		}

		if (total) {
			System.out.format("%s $%,.2f", "Value of current inventory: ", totalAmount);
		}

		new FileDataWriter().writeInventory(listOfInventory, total);

		LOG.info("Printed inventory");

	}

	@SuppressWarnings("static-access")
	public void printService(ArrayList<Service> listOfService, String make) throws ApplicationException {

		if (make != "false")
			listOfService = SortByMake.sortByMakeService(listOfService, make);

		System.out.println("Service Report");
		System.out.println(HORIZONTAL_LINE);
		System.out.format(SERVICE_HEADER, "First Name", "Last Name", "Make", "Model", "Year", "Mileage");
		System.out.println(HORIZONTAL_LINE);

		for (Service i : listOfService) {
			System.out.format(SERVICE_FORMAT, i.getFirstName(), i.getLastName(), i.getMake(), i.getModel(), i.getYear(), i.getMileage());
		}

		new FileDataWriter().writeServiceReport(listOfService);

		LOG.info("Printed service");
	}

}
