package a00998715.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00998715.Bcmc;
import a00998715.data.inventory.Inventory;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class InventoryItemDialog extends JDialog {

	private static final Logger LOG = LogManager.getLogger(Bcmc.class);

	private final JPanel contentPanel = new JPanel();
	private JTextField motorcycleIdField;
	private JTextField descriptionField;
	private JTextField partNumberField;
	private JTextField priceField;
	private JTextField quantityField;

	private Inventory item;

	/**
	 * Create the dialog.
	 */
	public InventoryItemDialog(Inventory item) {
		this.item = item;

		createUI();

		setItem();

	}

	private void createUI() {
		LOG.debug("Item dialog create");

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						updateItem();
						InventoryItemDialog.this.dispose();
					}

				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						InventoryItemDialog.this.dispose();
					}

				});
			}
		}

		JLabel lblId = new JLabel("ID");
		contentPanel.add(lblId, "cell 0 0,alignx trailing");

		motorcycleIdField = new JTextField();
		motorcycleIdField.setEnabled(false);
		motorcycleIdField.setEditable(false);
		contentPanel.add(motorcycleIdField, "cell 1 0,growx");
		motorcycleIdField.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		contentPanel.add(lblDescription, "cell 0 1,alignx trailing");

		descriptionField = new JTextField();
		contentPanel.add(descriptionField, "cell 1 1,growx");
		descriptionField.setColumns(10);

		JLabel lblPartNumber = new JLabel("Part Number");
		contentPanel.add(lblPartNumber, "cell 0 2,alignx trailing");

		partNumberField = new JTextField();
		contentPanel.add(partNumberField, "cell 1 2,growx");
		partNumberField.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		contentPanel.add(lblPrice, "cell 0 3,alignx trailing");

		priceField = new JTextField();
		contentPanel.add(priceField, "cell 1 3,growx");
		priceField.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity");
		contentPanel.add(lblQuantity, "cell 0 4, alignx trailing");

		quantityField = new JTextField();
		contentPanel.add(quantityField, "cell 1 4, growx");
		quantityField.setColumns(10);
	}

	private void setItem() {
		LOG.debug("Item dialog set data");

		motorcycleIdField.setText(item.getMotorcycleId());
		descriptionField.setText(item.getDescription());
		partNumberField.setText(item.getPartNumber());
		priceField.setText(item.getPrice());
		quantityField.setText(item.getQuantity());
	}

	private void updateItem() {
		LOG.debug("Item dialog update");

		item.setMotorcycleId(motorcycleIdField.getText());
		item.setDescription(descriptionField.getText());
		item.setPartNumber(partNumberField.getText());
		item.setPrice(priceField.getText());
		item.setQuantity(quantityField.getText());
	}

	public Inventory getItem() {
		return item;
	}

}
