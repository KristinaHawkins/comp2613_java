/**
 * Project: SecondAssignment
 * File: InventoryReportSorting.java
 * Date: Mar 24, 2017
 * Time: 3:12:07 PM
 */
package a00998715.data.util;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.ApplicationException;
import a00998715.data.inventory.Inventory;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class InventoryReportSorting {
	private static final Logger LOG = LogManager.getLogger(InventoryReportSorting.class);

	public static class SortByCount {
		public static ArrayList<Inventory> sort(ArrayList<Inventory> inventoryList) throws ApplicationException {
			LOG.debug("sorted by count");

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

		private static int compareByCount(Inventory obj1, Inventory obj2) throws ApplicationException {
			return Integer.valueOf(obj1.getQuantity()) - Integer.valueOf(obj2.getQuantity());
		}
	}

	public static class SortByDescription {
		public static ArrayList<Inventory> sort(ArrayList<Inventory> inventoryList) throws ApplicationException {
			LOG.debug("sorted by description");

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

	public static class sortingByMake {

		public static ArrayList<Inventory> sort(ArrayList<Inventory> listOfInventory, String make) {
			LOG.debug("filtered by make");

			ArrayList<Inventory> result = new ArrayList<>();

			String pattern = "(?i)^" + make + "[+].*$";

			for (Inventory i : listOfInventory) {
				if (i.getMotorcycleId().matches(pattern))
					result.add(i);
			}

			return result;
		}
	}

}
