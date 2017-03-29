package a00998715.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.Bcmc;
import a00998715.data.inventory.Inventory;
import a00998715.data.inventory.InventoryDao;

@SuppressWarnings("serial")
public class InventoryDialog extends JDialog {

	private static final Logger LOG = LogManager.getLogger(Bcmc.class);

	private final JPanel contentPanel = new JPanel();

	private InventoryListModel inventoryListModel;
	private JList<Inventory> itemsList;

	/**
	 * Create the dialog.
	 */
	public InventoryDialog() {

		createUI();

		addEventHandlers();

		setData();

	}

	private void createUI() {
		LOG.debug("Inventory dialog create ui");

		setBounds(100, 100, 450, 300);
		setSize(530, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.setActionCommand("Cancel");
				closeButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						InventoryDialog.this.dispose();
					}

				});
				buttonPane.add(closeButton);
			}
		}

		inventoryListModel = new InventoryListModel();
		itemsList = new JList<>(inventoryListModel);
		itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(itemsList));

	}

	private void setData() {
		LOG.debug("Inventory dialog set data ");

		InventoryDao invDao;
		try {
			invDao = InventoryDao.getInstance();
			ArrayList<Inventory> inventoryItemsList = invDao.getInventoryList();

			for (Inventory i : inventoryItemsList) {
				inventoryListModel.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addEventHandlers() {
		LOG.debug("Inventory dialog add Event Handlers");
		itemsList.getSelectionModel().addListSelectionListener(new UIController.InventoryItemsSelectionHandler(itemsList));
	}

}
