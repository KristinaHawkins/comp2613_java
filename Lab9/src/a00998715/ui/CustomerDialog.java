package a00998715.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import a00998715.data.Customer;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class CustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldStreet;
	private JTextField textFieldCity;
	private JTextField textFieldPostalCode;
	private JTextField textFieldPhone;
	private JTextField textFieldEmail;
	private JTextField textFieldJoinedDate;

	/**
	 * Create the dialog.
	 * 
	 * @throws ParseException
	 */
	public CustomerDialog(Customer customer) throws ParseException {

		setBounds(100, 100, 450, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		{
			JLabel lblId = new JLabel("ID");
			contentPanel.add(lblId, "cell 0 0,alignx trailing");
		}
		{
			textFieldID = new JTextField();
			textFieldID.setEnabled(false);
			textFieldID.setEditable(false);
			contentPanel.add(textFieldID, "cell 1 0,growx");
			textFieldID.setColumns(10);
		}
		{
			JLabel lblFirstName = new JLabel("First Name");
			contentPanel.add(lblFirstName, "cell 0 1,alignx trailing");
		}
		{
			textFieldFirstName = new JTextField();
			textFieldFirstName.setEditable(false);
			contentPanel.add(textFieldFirstName, "cell 1 1,growx");
			textFieldFirstName.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			contentPanel.add(lblLastName, "cell 0 2,alignx trailing");
		}
		{
			textFieldLastName = new JTextField();
			textFieldLastName.setEditable(false);
			contentPanel.add(textFieldLastName, "cell 1 2,growx");
			textFieldLastName.setColumns(10);
		}
		{
			JLabel lblStreet = new JLabel("Street");
			contentPanel.add(lblStreet, "cell 0 3,alignx trailing");
		}
		{
			textFieldStreet = new JTextField();
			textFieldStreet.setEditable(false);
			contentPanel.add(textFieldStreet, "cell 1 3,growx");
			textFieldStreet.setColumns(10);
		}
		{
			JLabel lblCity = new JLabel("City");
			contentPanel.add(lblCity, "cell 0 4,alignx trailing");
		}
		{
			textFieldCity = new JTextField();
			textFieldCity.setEditable(false);
			contentPanel.add(textFieldCity, "cell 1 4,growx");
			textFieldCity.setColumns(10);
		}
		{
			JLabel lblPostalCode = new JLabel("Postal Code");
			contentPanel.add(lblPostalCode, "cell 0 5,alignx trailing");
		}
		{
			textFieldPostalCode = new JTextField();
			textFieldPostalCode.setEditable(false);
			contentPanel.add(textFieldPostalCode, "cell 1 5,growx");
			textFieldPostalCode.setColumns(10);
		}
		{
			JLabel lblPhone = new JLabel("Phone");
			contentPanel.add(lblPhone, "cell 0 6,alignx trailing");
		}
		{
			textFieldPhone = new JTextField();
			textFieldPhone.setEditable(false);
			contentPanel.add(textFieldPhone, "cell 1 6,growx");
			textFieldPhone.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			contentPanel.add(lblEmail, "cell 0 7,alignx trailing");
		}
		{
			textFieldEmail = new JTextField();
			textFieldEmail.setEditable(false);
			contentPanel.add(textFieldEmail, "cell 1 7,growx");
			textFieldEmail.setColumns(10);
		}
		{
			JLabel lblJoinedDate = new JLabel("Joined Date");
			contentPanel.add(lblJoinedDate, "cell 0 8,alignx trailing");
		}
		{
			textFieldJoinedDate = new JTextField();
			textFieldJoinedDate.setEditable(false);
			contentPanel.add(textFieldJoinedDate, "cell 1 8,growx");
			textFieldJoinedDate.setColumns(10);
		}
		{
			Date date = new SimpleDateFormat("MMM dd yyyy").parse(customer.getDate());

			textFieldID.setText(String.valueOf(customer.getId()));
			textFieldFirstName.setText(customer.getFirstName());
			textFieldLastName.setText(customer.getLastName());
			textFieldStreet.setText(customer.getStreetName());
			textFieldCity.setText(customer.getCity());
			textFieldPostalCode.setText(customer.getPostalCode().replaceAll(" ", ""));
			textFieldPhone.setText(customer.getPhoneNumber().replaceAll("\\D", ""));
			textFieldEmail.setText(customer.getEmail());
			textFieldJoinedDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CustomerDialog.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CustomerDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
