package librarysystem.libpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OverdueCheckoutPanel extends JPanel {
	public static final OverdueCheckoutPanel INSTANCE = new OverdueCheckoutPanel();
	private JTable tContent;
	private JLabel lHeader;
	private JLabel lIsbn;
	private JComboBox cbIsbn;
	private JButton btnSubmit;
	private JScrollPane spContent;
	/**
	 * Create the panel.
	 */
	private OverdueCheckoutPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		
		lHeader = new JLabel("Overdue Checkout");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 10, 430, 20);
		add(lHeader);
		
		lIsbn = new JLabel("ISBN");
		lIsbn.setBounds(75, 44, 45, 13);
		add(lIsbn);
		
		cbIsbn = new JComboBox();
		cbIsbn.setBounds(130, 40, 113, 21);
		add(cbIsbn);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(249, 40, 85, 21);
		add(btnSubmit);
		
		spContent = new JScrollPane();
		spContent.setBounds(10, 73, 430, 217);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);
	}

	public void init() {
		try {
			OverdueCheckoutPanel panel = new OverdueCheckoutPanel();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
