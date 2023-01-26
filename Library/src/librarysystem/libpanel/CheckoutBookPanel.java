package librarysystem.libpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CheckoutBookPanel extends JPanel {
	private JTable tContent;

	/**
	 * Create the panel.
	 */
	public CheckoutBookPanel() {
		setLayout(null);
		
		JLabel lHeader = new JLabel("Checkout Book");
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setBounds(10, 10, 430, 22);
		add(lHeader);
		
		JLabel lId = new JLabel("Member ID");
		lId.setBounds(10, 42, 77, 13);
		add(lId);
		
		JComboBox tfId = new JComboBox();
		tfId.setBounds(97, 38, 85, 21);
		add(tfId);
		
		JLabel lIsbn = new JLabel("ISBN");
		lIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lIsbn.setBounds(192, 42, 45, 13);
		add(lIsbn);
		
		JButton bCheckout = new JButton("Checkout");
		bCheckout.setBounds(355, 38, 85, 21);
		add(bCheckout);
		
		JComboBox cbIsbn = new JComboBox();
		cbIsbn.setBounds(247, 38, 85, 21);
		add(cbIsbn);
		
		JScrollPane spContent = new JScrollPane();
		spContent.setBounds(10, 65, 430, 225);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);

	}

}
