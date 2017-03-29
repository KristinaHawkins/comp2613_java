/**
 * Project: SecondAssignment
 * File: InventoryModel.java
 * Date: Mar 23, 2017
 * Time: 12:33:05 PM
 */
package a00998715.ui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.Bcmc;
import a00998715.data.inventory.Inventory;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
@SuppressWarnings("serial")
public class InventoryListModel extends AbstractListModel<Inventory> {

	private static final Logger LOG = LogManager.getLogger(Bcmc.class);

	private ArrayList<Inventory> itemsList;

	public InventoryListModel() {
		itemsList = new ArrayList<>();
	}

	@Override
	public int getSize() {
		return itemsList == null ? 0 : itemsList.size();
	}

	@Override
	public Inventory getElementAt(int index) {
		return itemsList.get(index);
	}

	public void setInventoryItems(ArrayList<Inventory> itemsList) {
		this.itemsList = itemsList;

	}

	public ArrayList<Inventory> getInventoryItems() {
		return itemsList;
	}

	public void update(int index, Inventory item) {
		LOG.debug("InventoryListModel update item " + item.toString());
		itemsList.set(index, item);

		fireContentsChanged(this, index, index);
	}

	public void add(Inventory item) {
		LOG.debug("InventoryListModel add item " + item.toString());
		add(-1, item);
	}

	public void add(int index, Inventory item) {
		if (index == -1) {
			itemsList.add(item);
			index = getSize() - 1;
		} else {
			itemsList.add(index, item);
		}

		fireContentsChanged(this, index, index);
	}

}
