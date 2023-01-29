package librarysystem.adminpanel;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;
import java.util.List;
import java.util.regex.Pattern;

public class AddMemberPanel extends JPanel {
	public static final AddMemberPanel INSTANCE = new AddMemberPanel();
	private JTextField tfId;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfPhone;
	private JTextField tfStreet;
	private JTextField tfCity;
	private JTextField tfState;
	private JTextField tfZipCode;
	private JLabel lHeader;
	private JLabel lId;
	private JLabel lFirstName;
	private JLabel lLastName;
	private JLabel lPhone;
	private JLabel lAddress;
	private JLabel lStreet;
	private JLabel lCity;
	private JLabel lState;
	private JLabel lZipCode;
	private JButton btnAdd;
	/**
	 * Create the panel.
	 */
	private AddMemberPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		
		lHeader = new JLabel("Add Mew Member");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 5, 430, 20);
		add(lHeader);
		
		lId = new JLabel("ID");
		lId.setBounds(10, 35, 73, 13);
		add(lId);
		
		tfId = new JTextField();
		tfId.setBounds(93, 32, 347, 19);
		add(tfId);
		tfId.setColumns(10);
		
		lFirstName = new JLabel("First Name");
		lFirstName.setBounds(10, 61, 73, 13);
		add(lFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(93, 58, 136, 19);
		add(tfFirstName);
		
		lLastName = new JLabel("Last Name");
		lLastName.setBounds(232, 61, 52, 13);
		add(lLastName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(288, 58, 152, 19);
		add(tfLastName);
		
		lPhone = new JLabel("Phone");
		lPhone.setBounds(10, 84, 45, 13);
		add(lPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(93, 81, 347, 19);
		add(tfPhone);
		tfPhone.setColumns(10);
		
		lAddress = new JLabel("Address");
		lAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lAddress.setBounds(10, 113, 45, 20);
		add(lAddress);
		
		lStreet = new JLabel("Street");
		lStreet.setBounds(10, 143, 45, 13);
		add(lStreet);
		
		tfStreet = new JTextField();
		tfStreet.setBounds(93, 140, 136, 19);
		add(tfStreet);
		tfStreet.setColumns(10);
		
		lCity = new JLabel("City");
		lCity.setBounds(239, 143, 45, 13);
		add(lCity);
		
		tfCity = new JTextField();
		tfCity.setBounds(288, 140, 152, 19);
		add(tfCity);
		tfCity.setColumns(10);
		
		lState = new JLabel("state");
		lState.setBounds(10, 190, 45, 13);
		add(lState);
		
		tfState = new JTextField();
		tfState.setBounds(93, 187, 136, 19);
		add(tfState);
		tfState.setColumns(10);
		
		lZipCode = new JLabel("Zip code ");
		lZipCode.setBounds(239, 190, 45, 13);
		add(lZipCode);
		
		tfZipCode = new JTextField();
		tfZipCode.setBounds(288, 187, 152, 19);
		add(tfZipCode);
		tfZipCode.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(185, 241, 85, 21);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText();
				String firstName = tfFirstName.getText();
				String lastName = tfLastName.getText();
				String street = tfStreet.getText();
				String city = tfCity.getText();
				String state = tfState.getText();
				String phone = tfPhone.getText();
				String zipCode = tfZipCode.getText();

				ControllerInterface ci = new SystemController();
				List<String> listId = ci.allMemberIds();
				
				if(id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty() || phone.isEmpty() || zipCode.isEmpty()) {
					JOptionPane.showMessageDialog(AddMemberPanel.this, "Please fill all information");
				} else if (Pattern.matches("[a-zA-Z]+",phone) || phone.length() != 10) {
					JOptionPane.showMessageDialog(AddMemberPanel.this, "Phone should be number and have 10 digits");
				} else if (listId.contains(tfId.getText())) {
					JOptionPane.showMessageDialog(AddMemberPanel.this, "Id already existed");
				} else if(Pattern.matches("[a-zA-Z]+", zipCode) || zipCode.length() != 5) {
					JOptionPane.showMessageDialog(AddMemberPanel.this, "Zipcode should be number and have 5 digits");
				} else {
					Address add = new Address(street, city, state, zipCode);
					LibraryMember member = new LibraryMember(id, firstName, lastName, phone, add);
					ci.addNewMemberController(member);
					JOptionPane.showMessageDialog(AddMemberPanel.this, "Saved");
				}


			}
		});
		add(btnAdd);
	}

	public void init() {
		try {
			AddMemberPanel panel = new AddMemberPanel();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
