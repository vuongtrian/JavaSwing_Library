package librarysystem.adminpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditMemberPanel extends JPanel {
	private JTextField tfFirstName;
	private JTextField tfStreet;
	private JTextField tfCity;
	private JTextField tfState;
	private JTextField tfZipCode;
	private JTextField tfLastName;
	private JTextField tfPhone;

	/**
	 * Create the panel.
	 */
	public EditMemberPanel() {
		setLayout(null);
		
		JLabel lHeader = new JLabel("Edit Member");
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setBounds(10, 10, 430, 21);
		add(lHeader);
		
		JLabel lMemberId = new JLabel("Memeber Id");
		lMemberId.setBounds(36, 41, 78, 13);
		add(lMemberId);
		
		JComboBox cbMemberId = new JComboBox();
		cbMemberId.setBounds(104, 41, 107, 21);
		add(cbMemberId);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(104, 80, 107, 19);
		add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lFirstName = new JLabel("First Name");
		lFirstName.setBounds(36, 83, 58, 13);
		add(lFirstName);
		
		JLabel llastName = new JLabel("Last Name");
		llastName.setBounds(221, 83, 58, 13);
		add(llastName);
		
		JLabel lAddress = new JLabel("Address");
		lAddress.setBounds(36, 138, 45, 13);
		add(lAddress);
		
		JLabel lStreet = new JLabel("Street");
		lStreet.setBounds(36, 167, 45, 13);
		add(lStreet);
		
		JLabel lState = new JLabel("State");
		lState.setBounds(36, 209, 45, 13);
		add(lState);
		
		JLabel lCity = new JLabel("City");
		lCity.setBounds(223, 167, 45, 13);
		add(lCity);
		
		JLabel lZipCode = new JLabel("Zip Code");
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
		
		JButton bSave = new JButton("Save");
		bSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bSave.setBounds(183, 249, 85, 21);
		add(bSave);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(104, 110, 96, 19);
		add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel lPhone = new JLabel("Phone");
		lPhone.setBounds(36, 115, 45, 13);
		add(lPhone);

	}
}
