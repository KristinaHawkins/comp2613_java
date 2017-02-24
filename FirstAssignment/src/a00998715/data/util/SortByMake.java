/**
 * Project: FirstAssignment
 * File: SortByName.java
 * Date: Feb 21, 2017
 * Time: 10:26:47 AM
 */
package a00998715.data.util;

import java.util.ArrayList;

import a00998715.data.Inventory;
import a00998715.data.Service;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class SortByMake {

	public static ArrayList<Inventory> sortByMakeInventory(ArrayList<Inventory> listOfInventory, String make) {
		ArrayList<Inventory> result = new ArrayList<>();

		String pattern = "^" + make + "[+].*$";

		for (Inventory i : listOfInventory) {
			if (i.getMotorcycleId().matches(pattern))
				result.add(i);
		}

		return result;
	}

	public static ArrayList<Service> sortByMakeService(ArrayList<Service> listOfService, String make) {

		ArrayList<Service> result = new ArrayList<>();

		for (Service i : listOfService) {
			if (i.getMake().equals(make))
				result.add(i);
		}

		return result;
	}

}
