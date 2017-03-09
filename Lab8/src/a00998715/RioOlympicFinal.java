/**
 * Project: Lab8
 * File: RioOlympicFinal.java
 * Date: Mar 2, 2017
 * Time: 10:24:47 PM
 */
package a00998715;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import a00998715.data.Runner;
import a00998715.data.util.CompareByResult;
import a00998715.io.Report;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class RioOlympicFinal {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Instant startTime = Instant.now();
		System.out.println(startTime);

		start();

		Instant endTime = Instant.now();
		System.out.println(endTime);
		System.out.println("Duration: " + Duration.between(startTime, endTime).toMillis());
	}

	public static void start() throws Exception {

		Report report = new Report();

		ArrayList<Runner> runnersList = report.createRunnersList();

		for (Runner r : runnersList) {
			r.start();
		}

		for (Runner r : runnersList) {
			r.join();
		}

		report.setRunnersList(CompareByResult.sortByResult(runnersList));
		report.print();

	}

}
