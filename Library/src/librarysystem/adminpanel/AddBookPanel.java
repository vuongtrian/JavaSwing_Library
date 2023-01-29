package librarysystem.adminpanel;

import business.Author;
import business.Book;
import business.ControllerInterface;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class AddBookPanel extends JPanel {
	public static final AddBookPanel INSTANCE = new AddBookPanel();
	private JTextField tfIsbn;
	private JTextField tfTitle;
	private JTextField tfCopy;
	private JTextField tfAuthors;
	private JTable tContent;
	private JLabel lHeader;
	private JLabel lIsbn;
	private JLabel lTitle;
	private JLabel lAuthors;
	private JLabel lMaxCheckOut;
	private JLabel lCopies;
	private JButton bAuthors;
	private JComboBox cbMaxCheckOut;
	private JButton btnAdd;
	private JScrollPane spContent;
	ControllerInterface ci = new SystemController();
	private HashMap<String, Book> bookList = ci.allBooks();
	AuthorFrame authorFrame = new AuthorFrame();
	/**
	 * Create the panel.
	 */

	private AddBookPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));


		lHeader = new JLabel("Add Book");
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setBounds(10, 10, 430, 19);
		add(lHeader);
		
		lIsbn = new JLabel("ISBN");
		lIsbn.setBounds(10, 49, 45, 13);
		add(lIsbn);
		
		tfIsbn = new JTextField();
		tfIsbn.setBounds(74, 46, 142, 19);
		add(tfIsbn);
		tfIsbn.setColumns(10);
		
		lTitle = new JLabel("Title");
		lTitle.setBounds(240, 49, 37, 13);
		add(lTitle);
		
		tfTitle = new JTextField();
		tfTitle.setBounds(287, 46, 153, 19);
		add(tfTitle);
		tfTitle.setColumns(10);
		
		lAuthors = new JLabel("Authors");
		lAuthors.setBounds(10, 82, 45, 13);
		add(lAuthors);
		
		lMaxCheckOut = new JLabel("Max CheckOut");
		lMaxCheckOut.setBounds(240, 105, 82, 13);
		add(lMaxCheckOut);
		
		lCopies = new JLabel("Copies");
		lCopies.setBounds(10, 105, 45, 13);
		add(lCopies);
		
		tfCopy = new JTextField();
		tfCopy.setBounds(74, 102, 142, 19);
		add(tfCopy);
		tfCopy.setColumns(10);
		
		cbMaxCheckOut = new JComboBox();
		cbMaxCheckOut.setModel(new DefaultComboBoxModel(new String[] {"7", "21"}));
		cbMaxCheckOut.setBounds(332, 101, 108, 21);
		add(cbMaxCheckOut);
		
		tfAuthors = new JTextField();
		tfAuthors.setBounds(74, 75, 311, 19);
		List<Author> authorList = authorFrame.getAuthorList();

		tfAuthors.setEnabled(false);
		add(tfAuthors);
		tfAuthors.setColumns(10);
		
		bAuthors = new JButton("+");
		bAuthors.setFont(new Font("Tahoma", Font.PLAIN, 7));
		bAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				for (Author a: authorList) {
					sb.append(a.getFirstName());
					sb.append(", ");
				}
				tfAuthors.setText(sb.toString());
				authorFrame.setVisible(true);
			}
		});
		bAuthors.setBounds(395, 75, 45, 19);
		add(bAuthors);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(355, 132, 85, 21);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String isbn = tfIsbn.getText();
				String title = tfTitle.getText();
				List<Author> authors = authorFrame.getAuthorList();
				int copy;
				if(tfCopy.getText().isEmpty() || tfCopy.getText().length() == 0) {
					copy = 0;
				} else {
					copy = Integer.parseInt(tfCopy.getText());
				}
				int maxCheckout = Integer.parseInt(cbMaxCheckOut.getSelectedItem().toString()) ;

				if(isbn.isEmpty() || title.isEmpty() || authors.isEmpty()) {
					JOptionPane.showMessageDialog(AddBookPanel.this, "Please fill all information");
				} else if(ci.allBookIds().contains(isbn)) {
					JOptionPane.showMessageDialog(AddBookPanel.this, "ISBN already existed");
				} else {
					Book book = new Book(isbn, title, maxCheckout, authors, copy);
					ci.addBookController(book);
					defineTable();
				}
			}
		});
		add(btnAdd);

		spContent = new JScrollPane();
		spContent.setBounds(10, 163, 430, 127);
		add(spContent);

		defineTable();
/*		tableModel.addRow(books.toArray());*/
		spContent.add(tContent);
		spContent.setViewportView(tContent);
//		spContent.setColumnHeaderView(tContent);
	}


	public void publishAuthor(List<Author> authorList) {
		StringBuilder sb = new StringBuilder();
		for (Author a: authorList) {
			sb.append(a.getFirstName());
			sb.append(", ");
		}
		tfAuthors.setText(sb.toString());
		authorFrame.setVisible(true);
	}


	public void defineTable() {
		String[] column = {"ISBN", "Title", "Max Checkout", "Number of Copies"};
		List<Book> books = new ArrayList<>();
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		tContent = new JTable(tableModel);
		Iterator iterator = bookList.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry)iterator.next();
			Book book = (Book) mapElement.getValue();

			Object[] row = {book.getIsbn(), book.getTitle(), book.getMaxCheckoutLength(), book.getCopyNums()};
			tableModel.addRow(row);
		}
		System.out.println(books.toString());
		tContent.setSize(new Dimension(500,300));
		tContent.setBackground(new Color(224,224,224));
	}
}
