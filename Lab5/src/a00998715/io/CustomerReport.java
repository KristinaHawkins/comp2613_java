/**
 * Project: Lab4
 * File: CustomerReport.java
 * Date: Jan 22, 2017
 * Time: 3:41:43 PM
 */
package a00998715.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import a00998715.data.ApplicationException;
import a00998715.data.Customer;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class CustomerReport {

	private static final String HORIZONTAL_LINE = "-------------------------------------------------------------------------------------------------------------------------------------------------------------------";
	private static final String HEADER_FORMAT = " %2s %-6s %-15s %-15s %-25s %-15s %-15s %-15s %-25s %-15s\n";
	private static final String CUSTOMER_FORMAT = "%3s %06d %-15s %-15s %-25s %-15s %-15s %-15s %-25s %-15s\n";

	private ArrayList<Customer> listOfCustomers = new ArrayList<>();

	public void addCustomer(Customer customer) {
		listOfCustomers.add(customer);
	}

	public void addAllCustomers(ArrayList<Customer> customers) {
		listOfCustomers.addAll(customers);
	}

	public void printIntoFile() throws ApplicationException {

		PrintWriter outputStream = null;
		File sourceFile = new File("customers_report.txt");

		try {
			outputStream = new PrintWriter(new FileWriter(sourceFile));

			outputStream.println("Customers Report");
			outputStream.println(HORIZONTAL_LINE);
			outputStream.println(String.format(HEADER_FORMAT, "#.", "ID", "First Name", "Last Name", "Street", "City", "Postal Code", "Phone",
					"Email", "Join Date"));
			outputStream.println(HORIZONTAL_LINE);

			int counter = 1;
			for (Customer i : listOfCustomers) {
				outputStream.println(String.format(CUSTOMER_FORMAT, counter++ + ".", i.getId(), i.getFirstName(), i.getLastName(), i.getStreetName(),
						i.getCity(), i.getPostalCode(), i.getPhoneNumber(), i.getEmail(), i.getDate()));
			}
		} catch (IOException e) {
			throw new ApplicationException(e.getMessage());
		} finally {
			outputStream.close();
		}

	}
}
