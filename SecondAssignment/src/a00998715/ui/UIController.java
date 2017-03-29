/**
 * Project: SecondAssignment
 * File: UIController.java
 * Date: Mar 23, 2017
 * Time: 11:50:21 AM
 */
package a00998715.ui;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.Bcmc;
import a00998715.data.inventory.Inventory;
import a00998715.data.inventory.InventoryDao;
import a00998715.data.util.InventoryReportSorting;

/**
 * @author Edgar Zapeka, A00998715
 *
 */
public class UIController {

	private static final Logger LOG = LogManager.getLogger();

	private static JFrame mainFrame;

	@SuppressWarnings("static-access")
	public UIController(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	protected static class InventoryMenuItemHandeler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				LOG.debug("Inventory item pressed");
				InventoryDialog dialog = new InventoryDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
				LOG.error(exception.getMessage());
			}
		}

	}

	protected static class ExitMenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Instant endTime = Instant.now();
			LOG.info("End: " + endTime);
			LOG.info(String.format("Duration: %d ms", Duration.between(Bcmc.startTime, endTime).toMillis()));
			System.exit(0);
		}
	}

	protected static class AboutMenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LOG.debug("About menu item pressed.");
			JOptionPane.showMessageDialog(mainFrame, "Second Assignment\nEdgar Zapeka A00998715");
		}

	}

	protected static class TotalMenuItemHandel implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LOG.debug("Total menu item pressed.");
			long total = 0;
			try {
				InventoryDao invDao = InventoryDao.getInstance();
				ArrayList<Inventory> itemsList = invDao.getInventoryList();
				for (Inventory i : itemsList) {
					total += (Integer.valueOf(i.getPrice()) * Integer.valueOf(i.getQuantity()));
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				LOG.error(exception.getMessage());
			}
			JOptionPane.showMessageDialog(mainFrame, "The total amount of the Inventory is " + total);
		}
	}

	protected static class byCountMenuItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LOG.debug("By count menu item pressed.");
			try {
				InventoryDao invDao = InventoryDao.getInstance();
				ArrayList<Inventory> itemsList = InventoryReportSorting.SortByCount.sort(invDao.getInventoryList());
				InventoryReportDialog dialog = new InventoryReportDialog(itemsList, "By Count");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception exception) {

			}
		}
	}

	protected static class makeMenuItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LOG.debug("Make menu item pressed.");
			ArrayList<Inventory> itemsList;
			String make = JOptionPane.showInputDialog("Enter make value: \n");
			if (make == null) {
				return;
			}
			try {
				InventoryDao invDao = InventoryDao.getInstance();
				if (make.equals("")) {
					itemsList = invDao.getInventoryList();
				} else {
					itemsList = InventoryReportSorting.sortingByMake.sort(invDao.getInventoryList(), make);
				}
				if (itemsList.size() < 1) {
					JOptionPane.showMessageDialog(mainFrame, "No items found");
					return;
				}
				InventoryReportDialog dialog = new InventoryReportDialog(itemsList, "By Make");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
				LOG.error(exception.getMessage());
			}
		}
	}

	protected static class buDescriptionMenuItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LOG.debug("By description menu item pressed.");
			try {
				InventoryDao invDao = InventoryDao.getInstance();
				ArrayList<Inventory> itemsList = InventoryReportSorting.SortByDescription.sort(invDao.getInventoryList());
				InventoryReportDialog dialog = new InventoryReportDialog(itemsList, "By Description");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
				LOG.error(exception.getMessage());
			}
		}
	}

	protected static class InventoryItemsSelectionHandler implements ListSelectionListener {
		private JList<Inventory> itemsList;
		private InventoryListModel inventoryListModel;

		public InventoryItemsSelectionHandler(JList<Inventory> itemsList) {
			this.itemsList = itemsList;
			inventoryListModel = (InventoryListModel) itemsList.getModel();
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				return;
			}

			Inventory item = itemsList.getSelectedValue();
			if (item != null) {
				LOG.debug("Item selected: " + itemsList.getSelectedIndex());
				updateItem(item, itemsList.getSelectedIndex());
			}
		}

		private void updateItem(Inventory item, int index) {
			try {
				InventoryDao invDao = InventoryDao.getInstance();
				InventoryItemDialog dialog = new InventoryItemDialog(item);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setVisible(true);
				LOG.debug("Updating item: " + item.getMotorcycleId());
				item = dialog.getItem();
				invDao.update(item);
				inventoryListModel.update(index, item);
				LOG.debug("Ttem " + item.getMotorcycleId() + " updated");
			} catch (Exception exception) {
				exception.printStackTrace();
				LOG.error(exception.getMessage());
			}
			itemsList.clearSelection();
		}
	}

	protected static class CustomerMenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(mainFrame, "This feature is not available yet");
		}

	}

	protected static class ServiceMenuItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(mainFrame, "This feature is not available yet");
		}

	}
}
