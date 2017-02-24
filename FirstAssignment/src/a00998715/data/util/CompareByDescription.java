/**
 * Project: FirstAssignment
 * File: CompareByDescription.java
 * Date: Feb 21, 2017
 * Time: 12:53:45 AM
 */
package a00998715.data.util;

import java.util.ArrayList;

import a00998715.ApplicationException;
import a00998715.data.Inventory;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class CompareByDescription {

	public static ArrayList<Inventory> sortByDescription(ArrayList<Inventory> inventoryList) throws ApplicationException {

		boolean flag = true;
		Inventory temp;

		while (flag) {
			flag = false;
			for (int j = 0; j < inventoryList.size() - 1; j++) {
				if (compareTo(inventoryList.get(j), inventoryList.get(j + 1)) > 0) // change to < for descending sort
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

	public static ArrayList<Inventory> sortByDescriptionDescending(ArrayList<Inventory> inventoryList) throws ApplicationException {

		boolean flag = true;
		Inventory temp;

		while (flag) {
			flag = false;
			for (int j = 0; j < inventoryList.size() - 1; j++) {
				if (compareTo(inventoryList.get(j), inventoryList.get(j + 1)) < 0) // change to < for descending sort
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

	public static int compareTo(Inventory obj1, Inventory obj2) {
		int len1 = obj1.getDescription().length();
		int len2 = obj2.getDescription().length();
		int lim = Math.min(len1, len2);
		char v1[] = obj1.getDescription().toCharArray();
		char v2[] = obj2.getDescription().toCharArray();

		int k = 0;
		while (k < lim) {
			char c1 = v1[k];
			char c2 = v2[k];
			if (c1 != c2) {
				return c1 - c2;
			}
			k++;
		}
		return len1 - len2;
	}

}
