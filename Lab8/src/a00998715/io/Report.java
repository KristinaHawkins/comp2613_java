/**
 * Project: Lab8
 * File: Report.java
 * Date: Mar 3, 2017
 * Time: 3:22:42 PM
 */
package a00998715.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import a00998715.data.Runner;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class Report {

	private static final String HORIZONTAL_LINE = "=================================================================================";
	private static final String HEADER_FORMAT = "%-6s %-6s %-6s %-10s %-12s %-12s %-10s %8s \n";
	private static final String RUNNER_FORMAT = "%4d %6d %6d %8s %12s %14s %10.3f %10.3f \n";
	private static final String FIELD_DELIMITER = "\\|";

	private ArrayList<Runner> runnersListToPrint = new ArrayList<>();

	public ArrayList<Runner> getRunnersList() {
		return runnersListToPrint;
	}

	public void setRunnersList(ArrayList<Runner> runnersListToPrint) {
		this.runnersListToPrint = runnersListToPrint;
	}

	public ArrayList<Runner> createRunnersList() throws Exception {

		ArrayList<Runner> runnersList = new ArrayList<>();
		ArrayList<String> lines = readFile();
		String[] runnerData;

		for (String i : lines) {
			runnerData = i.split(FIELD_DELIMITER);

			runnersList.add(new Runner( //
					Integer.parseInt(runnerData[0]), //
					Integer.parseInt(runnerData[1]), //
					runnerData[2], //
					runnerData[4], //
					runnerData[3], //
					Double.parseDouble(runnerData[5]) //
			));
		}
		return runnersList;
	}

	private ArrayList<String> readFile() throws Exception {

		ArrayList<String> listOfRunners = new ArrayList<>();

		File sourceFile = new File("runners.txt");
		if (!sourceFile.exists()) {
			throw new Exception("File runners.txt not exist");
		}

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(sourceFile));
			String line = br.readLine(); // Skipping the first line
			while ((line = br.readLine()) != null) {
				listOfRunners.add(line);
			}
			br.close();
		} catch (IOException e) {
			throw new Exception(e.getCause());
		}

		return listOfRunners;
	}

	public void print() {

		System.out.println(HORIZONTAL_LINE);
		System.out.format(HEADER_FORMAT, "Rank", "Lane", "Bib#", "Country", "Last Name", "First Name", "Reaction", "Result");

		int counter = 1;
		for (Runner i : runnersListToPrint) {
			System.out.format(RUNNER_FORMAT, counter++, i.getLane(), i.getBibNumber(), i.getCountry(), i.getLastName(), i.getFirstName(),
					i.getReaction(), i.getResult());
		}
		System.out.println(HORIZONTAL_LINE);
	}
}
