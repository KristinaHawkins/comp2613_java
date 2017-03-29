package a00998715.ui;

/**
 * Project: SwingTest
 * File: InventoryTableModel.java
 * Date: Mar 26, 2017
 * Time: 11:08:32 AM
 */

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.Bcmc;
import a00998715.data.inventory.Inventory;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
@SuppressWarnings("serial")
public class InventoryTableModel extends AbstractTableModel {

	private static final Logger LOG = LogManager.getLogger(Bcmc.class);

	private static final String[] COLUMN_NAMES = { "Motorcycle_id", "Description", "Part Number", "Price", "Quantity" };

	private ArrayList<Inventory> invList;

	public InventoryTableModel() {
	}

	@Override
	public String getColumnName(int col) {
		return COLUMN_NAMES[col];
	}

	@Override
	public int getRowCount() {
		return invList.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return invList.get(rowIndex).getMotorcycleId();
		case 1:
			return invList.get(rowIndex).getDescription();
		case 2:
			return invList.get(rowIndex).getPartNumber();
		case 3:
			return invList.get(rowIndex).getPrice();
		case 4:
			return invList.get(rowIndex).getQuantity();
		default:
			return "";
		}
	}

	public void setInventoryItems(ArrayList<Inventory> invList) {
		LOG.debug("Set inventory items");
		this.invList = invList;
	}

}
