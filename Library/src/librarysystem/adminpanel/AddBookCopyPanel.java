package librarysystem.adminpanel;

import business.Book;
import business.ControllerInterface;
import business.SystemController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class AddBookCopyPanel extends JPanel {
	public static final AddBookCopyPanel INSTANCE = new AddBookCopyPanel();
	private JTextField tfIsbn;
	private JTable table;
	private JLabel lHeader;
	private JLabel lIsbn;
	private JButton btnAdd;
	private JScrollPane tContent;
	ControllerInterface ci = new SystemController();
	private HashMap<String, Book> bookList = ci.allBooks();
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
				if(tfIsbn.getText().isEmpty()) {
					JOptionPane.showMessageDialog(AddBookCopyPanel.this, "Please fill ISBN number");
				} else if (!ci.allBooks().containsKey(isbn)) {
					JOptionPane.showMessageDialog( AddBookCopyPanel.this,"Not found ISBN - " + isbn);
				} else {
					ci.addBookCopyController(isbn);
					JOptionPane.showMessageDialog(AddBookCopyPanel.this, "Added new copy");
				}

			}
		});
		add(btnAdd);
		
		tContent = new JScrollPane();
		tContent.setBounds(10, 79, 430, 211);
		add(tContent);

		defineTable();
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

	public void defineTable() {
		String[] column = {"ISBN", "Title", "Max Checkout", "Number of Copies"};
		List<Book> books = new ArrayList<>();
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		table = new JTable(tableModel);
		Iterator iterator = bookList.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry)iterator.next();
			Book book = (Book) mapElement.getValue();

			Object[] row = {book.getIsbn(), book.getTitle(), book.getMaxCheckoutLength(), book.getCopyNums()};
			tableModel.addRow(row);
		}
		table.setSize(new Dimension(500,300));
		table.setBackground(new Color(224,224,224));
	}
}
