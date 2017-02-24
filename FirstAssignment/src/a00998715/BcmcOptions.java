/**
 * 
 */
package a00998715;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Extract the program options from the commandline arguments and store them for safekeeping.
 * 
 * @author scirka
 *
 */
public class BcmcOptions {

	/**
	 * service Print the service report
	 * inventory Print the inventory report
	 * customers Print the customer report
	 * total Print the inventory report adding a Value column and calculated value for each part and the total value of the inventory is added to the
	 * end of the report.
	 * by_description Sorts the inventory report by part description name ascending. This is ignored if ‘inventory’ isn’t also specified
	 * by_count Sorts the inventory report by part count ascending
	 * by_join_date Sorts the customer report by join date
	 * make=<make> Filters the service or inventory report by make ascending
	 * desc Any sorted value is sorted in a descending order
	 */

	private static CommandLine commandLine;

	private BcmcOptions() {

	}

	/**
	 * Process the commandline arguments and set the program options.
	 * Although it may be overkill to use apache commons CLI to process the commandline, its used use to demonstrate its use.
	 * 
	 * @param args
	 *            Commandline arguments.
	 * @throws ParseException
	 */
	public static void process(String[] args) throws ParseException {
		commandLine = new DefaultParser().parse(createOptions(), args);
	}

	/**
	 * @return the commandLine
	 */
	public static CommandLine getCommandLine() {
		return commandLine;
	}

	private static Options createOptions() {
		// create Options object
		Options options = new Options();

		for (Value value : Value.values()) {
			Option option = null;

			if (value.hasArg) {
				option = Option.builder(value.option).longOpt(value.longOption).hasArg().desc(value.description).build();
			} else {
				option = Option.builder(value.option).longOpt(value.longOption).desc(value.description).build();
			}

			options.addOption(option);
		}

		return options;
	}

	public enum Value {
		SERVICE("s", "service", false, ""), //
		INVENTORY("i", "inventory", false, ""), //
		CUSTOMERS("c", "customers", false, ""), //
		TOTAL("t", "total", false, ""), //
		BY_DESCRIPTION("D", "by_description", false, ""), //
		BY_COUNT("C", "by_count", false, ""), //
		BY_JOIN_DATE("J", "by_join_date", false, ""), //
		MAKE("m", "make", true, ""), //
		DESCENDING("d", "descending", false, "");

		private final String option;
		private final String longOption;
		private final boolean hasArg;
		private final String description;

		Value(String option, String longOption, boolean hasArg, String description) {
			this.option = option;
			this.longOption = longOption;
			this.hasArg = hasArg;
			this.description = description;
		}

		/**
		 * @return the option
		 */
		public String getOption() {
			return option;
		}

		/**
		 * @return the longOption
		 */
		public String getLongOption() {
			return longOption;
		}

		/**
		 * @return the hasArg
		 */
		public boolean isHasArg() {
			return hasArg;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

	}

}
