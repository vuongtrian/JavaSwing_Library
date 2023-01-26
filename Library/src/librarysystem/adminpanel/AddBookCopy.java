package librarysystem.adminpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AddBookCopy extends JPanel {
	private JTextField tfIsbn;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AddBookCopy() {
		setLayout(null);
		
		JLabel lHeader = new JLabel("Add Book Copy");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 10, 430, 18);
		add(lHeader);
		
		JLabel lIsbn = new JLabel("ISBN");
		lIsbn.setBounds(20, 41, 45, 13);
		add(lIsbn);
		
		tfIsbn = new JTextField();
		tfIsbn.setBounds(89, 38, 235, 19);
		add(tfIsbn);
		tfIsbn.setColumns(10);
		
		JButton btnAdd = new JButton("Add a Copy");
		btnAdd.setBounds(355, 38, 85, 21);
		add(btnAdd);
		
		JScrollPane tContent = new JScrollPane();
		tContent.setBounds(10, 79, 430, 211);
		add(tContent);
		
		table = new JTable();
		tContent.setViewportView(table);

	}

}
