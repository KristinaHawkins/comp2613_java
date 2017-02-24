/**
 * Project: FirstAssignment
 * File: CompareByCount.java
 * Date: Feb 21, 2017
 * Time: 12:21:24 AM
 */
package a00998715.data.util;

import java.util.ArrayList;

import a00998715.ApplicationException;
import a00998715.data.Inventory;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class CompareByCount {

	public static ArrayList<Inventory> sortByCount(ArrayList<Inventory> inventoryList) throws ApplicationException {

		boolean flag = true;
		Inventory temp;

		while (flag) {
			flag = false;
			for (int j = 0; j < inventoryList.size() - 1; j++) {
				if (compareByCount(inventoryList.get(j), inventoryList.get(j + 1)) > 0) // change to < for descending sort
				{
					temp = inventoryList.get(j);
					inventoryList.set(j, inventoryList.get(j + 1));
					inventoryList.set(j + 1, temp);
					flag = true;
				}
			}
		}

		return inventoryList;
	}

	public static ArrayList<Inventory> sortByCountDescending(ArrayList<Inventory> inventoryList) throws ApplicationException {

		boolean flag = true;
		Inventory temp;

		while (flag) {
			flag = false;
			for (int j = 0; j < inventoryList.size() - 1; j++) {
				if (compareByCount(inventoryList.get(j), inventoryList.get(j + 1)) < 0) // change to < for descending sort
				{
					temp = inventoryList.get(j);
					inventoryList.set(j, inventoryList.get(j + 1));
					inventoryList.set(j + 1, temp);
					flag = true;
				}
			}
		}

		return inventoryList;
	}

	private static int compareByCount(Inventory obj1, Inventory obj2) throws ApplicationException {
		return obj1.getQuantity() - obj2.getQuantity();
	}

}
