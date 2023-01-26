package librarysystem.libpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CheckoutRecordPanel extends JPanel {
	private JTable tContent;

	/**
	 * Create the panel.
	 */
	public CheckoutRecordPanel() {
		setLayout(null);
		
		JLabel lHeader = new JLabel("Checkout Record");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 10, 430, 22);
		add(lHeader);
		
		JLabel lId = new JLabel("Member ID");
		lId.setBounds(94, 46, 60, 13);
		add(lId);
		
		JComboBox cbId = new JComboBox();
		cbId.setBounds(164, 42, 93, 21);
		add(cbId);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(267, 42, 65, 21);
		add(btnSubmit);
		
		JScrollPane spContent = new JScrollPane();
		spContent.setBounds(10, 69, 430, 221);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);

	}

}
