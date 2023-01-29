package librarysystem.libpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import business.CheckoutRecord;
import business.SystemController;
import service.Service;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CheckoutRecordPanel extends JPanel {
	public static final CheckoutRecordPanel INSTANCE = new CheckoutRecordPanel();
	private JTable tContent;
	private JLabel lHeader;
	private JLabel lId;
	private JComboBox cbId;
	private JButton btnSubmit;
	private JScrollPane spContent;
	/**
	 * Create the panel.
	 */
	private CheckoutRecordPanel() {
		SystemController ci = new SystemController();
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		
		lHeader = new JLabel("Checkout Record");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 10, 430, 22);
		add(lHeader);
		
		lId = new JLabel("Member ID");
		lId.setBounds(94, 46, 60, 13);
		add(lId);
		
		cbId = new JComboBox();
		cbId.setBounds(164, 42, 93, 21);
		List<String> members = ci.allMemberIds();
		cbId.setModel(new DefaultComboBoxModel<>(members.toArray(new String[0])));
		add(cbId);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(267, 42, 89, 21);
		add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Response"};
				List<CheckoutRecord> records= ci.findMemberCheckoutRecord(cbId.getSelectedItem().toString());
			    String[] data = { };
			    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			    
			    
			    for (CheckoutRecord line : records) {
			    	String properties = Service.findProperties(Arrays.asList("checkoutId", "memberId", "bookCopyId", "checkoutDate", "dueDate" ),
			                line.toString() );
			    	
			    	  for (String row : properties.split("\n")) {
			    		  Object[] rows = {row};
				            model.addRow(rows);
			    	  }
			    	  model.addRow(new   Object[] {});
		          
		        }
			    
			  //  model.addRow(data);  
			    tContent.setModel(model);
			  


			 
			//	model.setValueAt(), 0, 0);
	               // model.setValueAt(35, 0, 1);
	                // update the table to reflect the changes in the model
	                tContent.repaint();
			}
		});
		
		spContent = new JScrollPane();
		spContent.setBounds(10, 69, 430, 221);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);

	}

	public void init() {
		try {
			CheckoutRecordPanel panel = new CheckoutRecordPanel();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
