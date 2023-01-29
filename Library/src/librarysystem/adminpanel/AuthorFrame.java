package librarysystem.adminpanel;

import business.Address;
import business.Author;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AuthorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfPhone;
	private JTextField tfBio;
	private JTextField tfStreet;
	private JTextField tfCity;
	private JTextField tfState;
	private JTextField tfZipCode;

	private List<Author> authorList = new ArrayList<Author>();
	
	public AuthorFrame() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lHeader = new JLabel("Add Author");
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setBounds(10, 10, 416, 21);
		contentPane.add(lHeader);
		
		JLabel lFirstName = new JLabel("First Name");
		lFirstName.setBounds(10, 45, 67, 13);
		contentPane.add(lFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(87, 42, 96, 19);
		contentPane.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lLastName = new JLabel("Last Name");
		lLastName.setBounds(204, 45, 67, 13);
		contentPane.add(lLastName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(281, 42, 96, 19);
		contentPane.add(tfLastName);
		
		JLabel lPhone = new JLabel("Phone");
		lPhone.setBounds(10, 68, 67, 13);
		contentPane.add(lPhone);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(87, 65, 96, 19);
		contentPane.add(tfPhone);
		
		JLabel lBio = new JLabel("Short Bio");
		lBio.setBounds(204, 68, 67, 13);
		contentPane.add(lBio);
		
		tfBio = new JTextField();
		tfBio.setColumns(10);
		tfBio.setBounds(281, 65, 96, 19);
		contentPane.add(tfBio);
		
		JLabel lAddress = new JLabel("Adress");
		lAddress.setBounds(10, 106, 45, 13);
		contentPane.add(lAddress);
		
		JLabel lStreet = new JLabel("Street");
		lStreet.setBounds(10, 131, 67, 13);
		contentPane.add(lStreet);
		
		tfStreet = new JTextField();
		tfStreet.setColumns(10);
		tfStreet.setBounds(87, 128, 96, 19);
		contentPane.add(tfStreet);
		
		JLabel lCity = new JLabel("City");
		lCity.setBounds(204, 131, 67, 13);
		contentPane.add(lCity);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(281, 128, 96, 19);
		contentPane.add(tfCity);
		
		JLabel lState = new JLabel("State");
		lState.setBounds(10, 160, 67, 13);
		contentPane.add(lState);
		
		tfState = new JTextField();
		tfState.setColumns(10);
		tfState.setBounds(87, 157, 96, 19);
		contentPane.add(tfState);
		
		JLabel lZipCode = new JLabel("ZipCode");
		lZipCode.setBounds(204, 160, 67, 13);
		contentPane.add(lZipCode);
		
		tfZipCode = new JTextField();
		tfZipCode.setColumns(10);
		tfZipCode.setBounds(281, 157, 96, 19);
		contentPane.add(tfZipCode);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(135, 200, 85, 21);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Author author = getAuthor();
				authorList.add(author);
				AddBookPanel.INSTANCE.publishAuthor(authorList);
			}
		});
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(249, 200, 85, 21);
		contentPane.add(btnCancel);
	}

	public Author getAuthor() {
		String firstName = tfFirstName.getText();
		String lastName = tfLastName.getText();
		String phone = tfPhone.getText();
		String bio = tfBio.getText();
		String street = tfStreet.getText();
		String city = tfCity.getText();
		String state = tfState.getText();
		String zipCode = tfZipCode.getText();

		if(firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || bio.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty() || zipCode.isEmpty()) {
			JOptionPane.showMessageDialog(AuthorFrame.this, "Please fill all information");
		} else if(Pattern.matches("[a-zA-Z]+", phone) || phone.length() != 10) {
			JOptionPane.showMessageDialog(AuthorFrame.this, "Phone should be number and have 10 digits");
		} else if (Pattern.matches("[a-zA-Z]+", zipCode) || zipCode.length() != 5) {
			JOptionPane.showMessageDialog(AuthorFrame.this, "Zipcode should be number and have 5 digits");
		}
		Author author = new Author(firstName, lastName, phone, new Address(street, city, state, zipCode),bio);
		return author;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}
}
