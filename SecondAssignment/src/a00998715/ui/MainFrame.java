package a00998715.ui;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private static final Logger LOG = LogManager.getLogger();

	private JMenuItem mntmInventory;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JMenuItem mntmTotal;
	private JMenuItem mntmByCount;
	private JMenuItem mntmByDescription;
	private JMenuItem mntmMake;
	private JMenuItem mntmService;
	private JMenuItem mntmCustomers;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		createUI();
		addEventHandlers();

	}

	private void createUI() {
		LOG.debug("MainFrame UI create");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmExit = new JMenuItem("Quit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		mnFile.add(mntmExit);

		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);

		mntmCustomers = new JMenuItem("Customers");
		mntmCustomers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnData.add(mntmCustomers);

		mntmService = new JMenuItem("Service");
		mntmService.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mnData.add(mntmService);

		mntmInventory = new JMenuItem("Inventory");
		mntmInventory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mnData.add(mntmInventory);

		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);

		mntmTotal = new JMenuItem("Total");
		mntmTotal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnReports.add(mntmTotal);

		mntmByDescription = new JMenuItem("By Description");
		mntmByDescription.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		mnReports.add(mntmByDescription);

		mntmByCount = new JMenuItem("By Count");
		mntmByCount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
		mnReports.add(mntmByCount);

		mntmMake = new JMenuItem("Make");
		mntmMake.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		mnReports.add(mntmMake);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));

	}

	private void addEventHandlers() {
		LOG.debug("MainFrame add Event Handlers");
		new UIController(this);

		mntmCustomers.addActionListener(new UIController.CustomerMenuItemHandler());
		mntmService.addActionListener(new UIController.ServiceMenuItemHandler());
		mntmMake.addActionListener(new UIController.makeMenuItem());
		mntmByDescription.addActionListener(new UIController.buDescriptionMenuItem());
		mntmByCount.addActionListener(new UIController.byCountMenuItem());
		mntmTotal.addActionListener(new UIController.TotalMenuItemHandel());
		mntmAbout.addActionListener(new UIController.AboutMenuItemHandler());
		mntmExit.addActionListener(new UIController.ExitMenuItemHandler());
		mntmInventory.addActionListener(new UIController.InventoryMenuItemHandeler());
	}

}
