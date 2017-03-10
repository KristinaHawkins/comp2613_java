package a00998715.ui;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import a00998715.data.Customer;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame(ArrayList<Customer> customersList) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmCustomer = new JMenuItem("Customer");
		mntmCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Customer customer = selectRandomCustomer(customersList);
					CustomerDialog dialog = new CustomerDialog(customer);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModalityType(ModalityType.APPLICATION_MODAL);
					dialog.setVisible(true);
				} catch (Exception event) {
					event.printStackTrace();
				}
			}
		});
		mntmCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mnFile.add(mntmCustomer);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}

		});
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAbout.setContentAreaFilled(false);
		mntmAbout.setBorder(null);
		mntmAbout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, "Lab9\nEdgar Zapeka A00998715");
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));
	}

	public Customer selectRandomCustomer(ArrayList<Customer> customersList) {

		return customersList.get(new Random().nextInt(customersList.size()));

	}

}
