package librarysystem.adminpanel;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.regex.Pattern;

public class EditMemberPanel extends JPanel {
	public static final EditMemberPanel INSTANCE = new EditMemberPanel();
	private JTextField tfFirstName;
	private JTextField tfStreet;
	private JTextField tfCity;
	private JTextField tfState;
	private JTextField tfZipCode;
	private JTextField tfLastName;
	private JTextField tfPhone;
	private JLabel lHeader;
	private JLabel lMemberId;
	private JComboBox cbMemberId;
	private JLabel lFirstName;
	private JLabel llastName;
	private JLabel lAddress;
	private JLabel lStreet;
	private JLabel lState;
	private JLabel lCity;
	private JLabel lZipCode;
	private JButton bSave;
	private JLabel lPhone;
	/**
	 * Create the panel.
	 */
	private EditMemberPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		ControllerInterface ci = new SystemController();

		
		lHeader = new JLabel("Edit Member");
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setBounds(10, 10, 430, 21);
		add(lHeader);
		
		lMemberId = new JLabel("Memeber Id");
		lMemberId.setBounds(36, 41, 78, 13);
		add(lMemberId);

		cbMemberId = new JComboBox();
		List<String> allMember = ci.allMemberIds();
		cbMemberId.setModel(new DefaultComboBoxModel(allMember.toArray(new String[0])));
		cbMemberId.setBounds(104, 41, 107, 21);
		cbMemberId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedId = cbMemberId.getSelectedItem().toString();
				LibraryMember member = ci.getLibraryMemberByIdController(selectedId);
				tfFirstName.setText(member.getFirstName());
				tfLastName.setText(member.getLastName());
				tfPhone.setText(member.getTelephone());
				tfStreet.setText(member.getAddress().getStreet());
				tfCity.setText(member.getAddress().getCity());
				tfState.setText(member.getAddress().getState());
				tfZipCode.setText(member.getAddress().getZip());
			}
		});
		add(cbMemberId);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(104, 80, 107, 19);
		add(tfFirstName);
		tfFirstName.setColumns(10);
		
		lFirstName = new JLabel("First Name");
		lFirstName.setBounds(36, 83, 58, 13);
		add(lFirstName);
		
		llastName = new JLabel("Last Name");
		llastName.setBounds(221, 83, 58, 13);
		add(llastName);
		
		lAddress = new JLabel("Address");
		lAddress.setBounds(36, 138, 45, 13);
		add(lAddress);
		
		lStreet = new JLabel("Street");
		lStreet.setBounds(36, 167, 45, 13);
		add(lStreet);
		
		lState = new JLabel("State");
		lState.setBounds(36, 209, 45, 13);
		add(lState);
		
		lCity = new JLabel("City");
		lCity.setBounds(223, 167, 45, 13);
		add(lCity);
		
		lZipCode = new JLabel("Zip Code");
		lZipCode.setBounds(221, 209, 45, 13);
		add(lZipCode);
		
		tfStreet = new JTextField();
		tfStreet.setBounds(104, 164, 107, 19);
		add(tfStreet);
		tfStreet.setColumns(10);
		
		tfCity = new JTextField();
		tfCity.setBounds(297, 164, 120, 19);
		add(tfCity);
		tfCity.setColumns(10);
		
		tfState = new JTextField();
		tfState.setBounds(104, 206, 107, 19);
		add(tfState);
		tfState.setColumns(10);
		
		tfZipCode = new JTextField();
		tfZipCode.setBounds(297, 206, 120, 19);
		add(tfZipCode);
		tfZipCode.setColumns(10);
		
		tfLastName = new JTextField();
		tfLastName.setBounds(297, 80, 120, 19);
		add(tfLastName);
		tfLastName.setColumns(10);
		
		bSave = new JButton("Save");
		bSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bSave.setBounds(183, 249, 85, 21);
		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = cbMemberId.getSelectedItem().toString();
				String firstName = tfFirstName.getText();
				String lastName = tfLastName.getText();
				String street = tfStreet.getText();
				String city = tfCity.getText();
				String state = tfState.getText();
				String phone = tfPhone.getText();
				String zipCode = tfZipCode.getText();

				if(firstName.isEmpty() || lastName.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty() || phone.isEmpty() || zipCode.isEmpty()) {
					JOptionPane.showMessageDialog(EditMemberPanel.this, "Please fill all information");
				} else if (Pattern.matches("[a-zA-Z]+",phone) || phone.length() != 10) {
					JOptionPane.showMessageDialog(EditMemberPanel.this, "Phone should be number and have 10 digits");
				} else if(Pattern.matches("[a-zA-Z]+",zipCode) || zipCode.length() != 5) {
					JOptionPane.showMessageDialog(EditMemberPanel.this, "Zipcode should be number and have 5 digits");
				} else {
					LibraryMember member = new LibraryMember(id, firstName, lastName, phone, new Address(street, city, state,zipCode));
					ci.updateMemberController(member);
				}
			}
		});
		add(bSave);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(104, 110, 96, 19);
		add(tfPhone);
		tfPhone.setColumns(10);
		
		lPhone = new JLabel("Phone");
		lPhone.setBounds(36, 115, 45, 13);
		add(lPhone);
	}

	public void init() {
		try {
			EditMemberPanel panel = new EditMemberPanel();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
