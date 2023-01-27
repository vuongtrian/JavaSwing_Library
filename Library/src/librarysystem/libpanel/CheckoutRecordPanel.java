package librarysystem.libpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CheckoutRecordPanel extends JPanel {
	public static final CheckoutRecordPanel INSTANCE = new CheckoutRecordPanel();
	private JTable tContent;
	private JLabel lHeader;
	private JLabel lId;
	private JComboBox cbId;
	private JButton btnSubmit;
	private JScrollPane spContent;
	/**
	 * Create the panel.
	 */
	private CheckoutRecordPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		
		lHeader = new JLabel("Checkout Record");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 10, 430, 22);
		add(lHeader);
		
		lId = new JLabel("Member ID");
		lId.setBounds(94, 46, 60, 13);
		add(lId);
		
		cbId = new JComboBox();
		cbId.setBounds(164, 42, 93, 21);
		add(cbId);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(267, 42, 65, 21);
		add(btnSubmit);
		
		spContent = new JScrollPane();
		spContent.setBounds(10, 69, 430, 221);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);

	}

	public void init() {
		try {
			CheckoutRecordPanel panel = new CheckoutRecordPanel();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
