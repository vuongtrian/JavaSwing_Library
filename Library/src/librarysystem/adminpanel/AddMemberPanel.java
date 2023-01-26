package librarysystem.adminpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddMemberPanel extends JPanel {
	private JTextField tfId;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfPhone;
	private JTextField tfStreet;
	private JTextField tfCity;
	private JTextField tfState;
	private JTextField tfZipCode;

	/**
	 * Create the panel.
	 */
	public AddMemberPanel() {
		setLayout(null);
		
		JLabel lHeader = new JLabel("Add Mew Member");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 5, 430, 20);
		add(lHeader);
		
		JLabel lId = new JLabel("ID");
		lId.setBounds(10, 35, 73, 13);
		add(lId);
		
		tfId = new JTextField();
		tfId.setBounds(93, 32, 347, 19);
		add(tfId);
		tfId.setColumns(10);
		
		JLabel lFirstName = new JLabel("First Name");
		lFirstName.setBounds(10, 61, 73, 13);
		add(lFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(93, 58, 136, 19);
		add(tfFirstName);
		
		JLabel lLastName = new JLabel("Last Name");
		lLastName.setBounds(232, 61, 52, 13);
		add(lLastName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(288, 58, 152, 19);
		add(tfLastName);
		
		JLabel lPhone = new JLabel("Phone");
		lPhone.setBounds(10, 84, 45, 13);
		add(lPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(93, 81, 347, 19);
		add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel lAddress = new JLabel("Address");
		lAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lAddress.setBounds(10, 113, 45, 20);
		add(lAddress);
		
		JLabel lStreet = new JLabel("Street");
		lStreet.setBounds(10, 143, 45, 13);
		add(lStreet);
		
		tfStreet = new JTextField();
		tfStreet.setBounds(93, 140, 136, 19);
		add(tfStreet);
		tfStreet.setColumns(10);
		
		JLabel lCity = new JLabel("City");
		lCity.setBounds(239, 143, 45, 13);
		add(lCity);
		
		tfCity = new JTextField();
		tfCity.setBounds(288, 140, 152, 19);
		add(tfCity);
		tfCity.setColumns(10);
		
		JLabel lState = new JLabel("state");
		lState.setBounds(10, 190, 45, 13);
		add(lState);
		
		tfState = new JTextField();
		tfState.setBounds(93, 187, 136, 19);
		add(tfState);
		tfState.setColumns(10);
		
		JLabel lZipCode = new JLabel("Zip code ");
		lZipCode.setBounds(239, 190, 45, 13);
		add(lZipCode);
		
		tfZipCode = new JTextField();
		tfZipCode.setBounds(288, 187, 152, 19);
		add(tfZipCode);
		tfZipCode.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(185, 241, 85, 21);
		add(btnAdd);

	}
}
