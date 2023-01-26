package librarysystem.adminpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AddBookPanel extends JPanel {
	private JTextField tfIsbn;
	private JTextField tfTitle;
	private JTextField textField_4;
	private JTextField tfAuthors;
	private JTable tContent;

	/**
	 * Create the panel.
	 */
	public AddBookPanel() {
		setLayout(null);
		
		JLabel lHeader = new JLabel("Add Book");
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setBounds(10, 10, 430, 19);
		add(lHeader);
		
		JLabel lIsbn = new JLabel("ISBN");
		lIsbn.setBounds(10, 49, 45, 13);
		add(lIsbn);
		
		tfIsbn = new JTextField();
		tfIsbn.setBounds(74, 46, 142, 19);
		add(tfIsbn);
		tfIsbn.setColumns(10);
		
		JLabel lTitle = new JLabel("Title");
		lTitle.setBounds(240, 49, 37, 13);
		add(lTitle);
		
		tfTitle = new JTextField();
		tfTitle.setBounds(287, 46, 153, 19);
		add(tfTitle);
		tfTitle.setColumns(10);
		
		JLabel lAuthors = new JLabel("Authors");
		lAuthors.setBounds(10, 82, 45, 13);
		add(lAuthors);
		
		JLabel lMaxCheckOut = new JLabel("Max CheckOut");
		lMaxCheckOut.setBounds(240, 105, 82, 13);
		add(lMaxCheckOut);
		
		JLabel lCopies = new JLabel("Copies");
		lCopies.setBounds(10, 105, 45, 13);
		add(lCopies);
		
		textField_4 = new JTextField();
		textField_4.setBounds(74, 102, 142, 19);
		add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox cbMaxCheckOut = new JComboBox();
		cbMaxCheckOut.setModel(new DefaultComboBoxModel(new String[] {"7", "21"}));
		cbMaxCheckOut.setBounds(332, 101, 108, 21);
		add(cbMaxCheckOut);
		
		tfAuthors = new JTextField();
		tfAuthors.setBounds(74, 75, 311, 19);
		add(tfAuthors);
		tfAuthors.setColumns(10);
		
		JButton bAuthors = new JButton("+");
		bAuthors.setFont(new Font("Tahoma", Font.PLAIN, 7));
		bAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bAuthors.setBounds(395, 75, 45, 19);
		add(bAuthors);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(355, 132, 85, 21);
		add(btnAdd);
		
		JScrollPane spContent = new JScrollPane();
		spContent.setBounds(10, 163, 430, 127);
		add(spContent);
		
		tContent = new JTable();
		tContent.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		spContent.setColumnHeaderView(tContent);

	}
}
