package librarysystem.adminpanel;

import business.Book;
import business.ControllerInterface;
import business.SystemController;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AddBookCopyPanel extends JPanel {
	public static final AddBookCopyPanel INSTANCE = new AddBookCopyPanel();
	private JTextField tfIsbn;
	private JTable table;
	private JLabel lHeader;
	private JLabel lIsbn;
	private JButton btnAdd;
	private JScrollPane tContent;
	/**
	 * Create the panel.
	 */
	private AddBookCopyPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		ControllerInterface ci = new SystemController();

		lHeader = new JLabel("Add Book Copy");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 10, 430, 18);
		add(lHeader);
		
		lIsbn = new JLabel("ISBN");
		lIsbn.setBounds(20, 41, 45, 13);
		add(lIsbn);
		
		tfIsbn = new JTextField();
		tfIsbn.setBounds(89, 38, 235, 19);
		add(tfIsbn);
		tfIsbn.setColumns(10);
		
		btnAdd = new JButton("Add a Copy");
		btnAdd.setBounds(355, 38, 85, 21);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbn = tfIsbn.getText();
				Book book = ci.getBookByIsbnController(isbn);
			}
		});
		add(btnAdd);
		
		tContent = new JScrollPane();
		tContent.setBounds(10, 79, 430, 211);
		add(tContent);
		
		table = new JTable();
		tContent.setViewportView(table);
	}

	public void init() {
		try {
			AddBookCopyPanel panel = new AddBookCopyPanel();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
