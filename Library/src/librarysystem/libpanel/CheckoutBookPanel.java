package librarysystem.libpanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import business.SystemController;

public class CheckoutBookPanel extends JPanel {
	public static final CheckoutBookPanel INSTANCE = new CheckoutBookPanel();
	private JTable tContent;
	private JLabel lHeader;
	private JLabel lId;
	private JComboBox tfId;
	private JLabel lIsbn;
	private JButton bCheckout;
	private JComboBox cbIsbn;
	private JScrollPane spContent;
	/**
	 * Create the panel.
	 */
	private CheckoutBookPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		SystemController ci = new SystemController();
		
		lHeader = new JLabel("Checkout Book");
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setBounds(10, 10, 430, 22);
		add(lHeader);
		
		lId = new JLabel("Member ID");
		lId.setBounds(10, 42, 77, 13);
		add(lId);
		
		tfId = new JComboBox();
		tfId.setBounds(97, 38, 85, 21);
		List<String> members = ci.allMemberIds();
		tfId.setModel(new DefaultComboBoxModel<>(members.toArray(new String[0])));
		add(tfId);
		
		lIsbn = new JLabel("ISBN");
		lIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lIsbn.setBounds(192, 42, 45, 13);
		add(lIsbn);
		
		bCheckout = new JButton("Checkout");
		bCheckout.setBounds(355, 38, 85, 21);
		bCheckout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(bCheckout);
		
		cbIsbn = new JComboBox();
		cbIsbn.setBounds(247, 38, 85, 21);
		List<String> books = ci.allBookIds();
		cbIsbn.setModel(new DefaultComboBoxModel<>(books.toArray(new String[0])));
		add(cbIsbn);
		
		spContent = new JScrollPane();
		spContent.setBounds(10, 65, 430, 225);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);
	}

	public CheckoutBookPanel init() {
		CheckoutBookPanel panel;
		panel = new CheckoutBookPanel();
		panel.setVisible(true);
		return panel;
	}
}
