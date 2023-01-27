package librarysystem.libpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.*;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CheckoutBookPanel extends JPanel {
	public static final CheckoutBookPanel INSTANCE = new CheckoutBookPanel();
	private JTable tContent;
	private JLabel lHeader;
	private JLabel lId;
	private JComboBox tfId;
	private JLabel lIsbn;
	private JButton bCheckout;
	private JComboBox cbIsbn;
	private JScrollPane spContent;
	/**
	 * Create the panel.
	 */
	private CheckoutBookPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		
		lHeader = new JLabel("Checkout Book");
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setBounds(10, 10, 430, 22);
		add(lHeader);
		
		lId = new JLabel("Member ID");
		lId.setBounds(10, 42, 77, 13);
		add(lId);
		
		tfId = new JComboBox();
		tfId.setBounds(97, 38, 85, 21);
		add(tfId);
		
		lIsbn = new JLabel("ISBN");
		lIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lIsbn.setBounds(192, 42, 45, 13);
		add(lIsbn);
		
		bCheckout = new JButton("Checkout");
		bCheckout.setBounds(355, 38, 85, 21);
		add(bCheckout);
		
		cbIsbn = new JComboBox();
		cbIsbn.setBounds(247, 38, 85, 21);
		add(cbIsbn);
		
		spContent = new JScrollPane();
		spContent.setBounds(10, 65, 430, 225);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);
	}

	public CheckoutBookPanel init() {
		CheckoutBookPanel panel;
		panel = new CheckoutBookPanel();
		panel.setVisible(true);
		return panel;
	}
}
