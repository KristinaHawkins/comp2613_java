/**
 * Project: Lab2
 * File: CustomerReader.java
 * Date: Jan 22, 2017
 * Time: 3:41:56 PM
 */
package a00998715.io;

import a00998715.data.ApplicationException;
import a00998715.data.Customer;
import a00998715.data.util.Validator;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class CustomerReader {

	public static final String RECORD_DELIMITER = ":";
	public static final String FIELD_DELIMITER = "\\|";

	private String input;

	public CustomerReader(String input) {
		setInput(input);
	}

	private void setInput(String input) {
		if (input.length() != 0 && input != null) {
			this.input = input;
		}
	}

	/**
	 * This method split unformatted input string
	 * 
	 * @param input
	 *            input string
	 * @return Two dimensional String array. Each row contains record for one customer
	 */
	private String[][] splitData(String input) throws ApplicationException {

		String[] unFormattedArrayOfCustomers = input.split(RECORD_DELIMITER);
		String[][] readyForRecordArrayOfCustomers = new String[unFormattedArrayOfCustomers.length][9];

		for (int i = 0; i < unFormattedArrayOfCustomers.length; i++) {
			String[] customerInformation = unFormattedArrayOfCustomers[i].split(FIELD_DELIMITER);
			Validator.isAppropriateNumberOfFieldsForRecord(customerInformation);

			readyForRecordArrayOfCustomers[i][0] = customerInformation[0];
			readyForRecordArrayOfCustomers[i][1] = customerInformation[1];
			readyForRecordArrayOfCustomers[i][2] = customerInformation[2];
			readyForRecordArrayOfCustomers[i][3] = customerInformation[3];
			readyForRecordArrayOfCustomers[i][4] = customerInformation[4];
			readyForRecordArrayOfCustomers[i][5] = Validator.validatePostalCode(customerInformation[5]);
			readyForRecordArrayOfCustomers[i][6] = Validator.validatePhoneNumber(customerInformation[6]);
			readyForRecordArrayOfCustomers[i][7] = Validator.validateEmail(customerInformation[7]);
			readyForRecordArrayOfCustomers[i][8] = Validator.validateDateTime(customerInformation[8]);
		}

		return readyForRecordArrayOfCustomers;
	}

	/**
	 * The method creates and fills CustomerReport object
	 * 
	 * @return Filled and ready to use instance of CustomerReport
	 */
	public CustomerReport createCustomerReport() throws ApplicationException {
		String[][] readyForRecordArrayOfCustomers = splitData(input);
		CustomerReport report = new CustomerReport();
		Customer newCustomer;

		for (int i = 0; i < readyForRecordArrayOfCustomers.length; i++) {

			newCustomer = new Customer.Builder(Integer.parseInt(readyForRecordArrayOfCustomers[i][0]), readyForRecordArrayOfCustomers[i][6])
					.firstName(readyForRecordArrayOfCustomers[i][1]).lastName(readyForRecordArrayOfCustomers[i][2])
					.streetName(readyForRecordArrayOfCustomers[i][3]).city(readyForRecordArrayOfCustomers[i][4])
					.postalCode(readyForRecordArrayOfCustomers[i][5]).email(readyForRecordArrayOfCustomers[i][7])
					.date(readyForRecordArrayOfCustomers[i][8]).build();

			report.addCustomer(newCustomer);
		}

		return report;
	}

}
