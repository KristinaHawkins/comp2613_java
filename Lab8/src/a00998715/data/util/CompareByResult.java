/**
 * Project: Lab8
 * File: CompareByResult.java
 * Date: Mar 6, 2017
 * Time: 10:08:03 AM
 */
package a00998715.data.util;

import java.util.ArrayList;

import a00998715.data.Runner;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class CompareByResult {

	public static ArrayList<Runner> sortByResult(ArrayList<Runner> runnersList) {

		boolean flag = true;
		Runner temp;

		while (flag) {
			flag = false;
			for (int j = 0; j < runnersList.size() - 1; j++) {
				if (runnersList.get(j).compareTo(runnersList.get(j + 1)) > 0) // change to < for descending sort
				{
					temp = runnersList.get(j);
					runnersList.set(j, runnersList.get(j + 1));
					runnersList.set(j + 1, temp);
					flag = true;
				}
			}
		}

		return runnersList;
	}

}
