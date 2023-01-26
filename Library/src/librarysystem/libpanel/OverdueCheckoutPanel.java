package librarysystem.libpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OverdueCheckoutPanel extends JPanel {
	private JTable tContent;

	/**
	 * Create the panel.
	 */
	public OverdueCheckoutPanel() {
		setLayout(null);
		
		JLabel lHeader = new JLabel("Overdue Checkout");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 10, 430, 20);
		add(lHeader);
		
		JLabel lIsbn = new JLabel("ISBN");
		lIsbn.setBounds(75, 44, 45, 13);
		add(lIsbn);
		
		JComboBox cbIsbn = new JComboBox();
		cbIsbn.setBounds(130, 40, 113, 21);
		add(cbIsbn);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(249, 40, 85, 21);
		add(btnSubmit);
		
		JScrollPane spContent = new JScrollPane();
		spContent.setBounds(10, 73, 430, 217);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);

	}

}
