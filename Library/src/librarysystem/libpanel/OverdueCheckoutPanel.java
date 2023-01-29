package librarysystem.libpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import business.CheckoutRecord;
import business.SystemController;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OverdueCheckoutPanel extends JPanel {
	public static final OverdueCheckoutPanel INSTANCE = new OverdueCheckoutPanel();
	private JTable tContent;
	private JLabel lHeader;
	private JLabel lIsbn;
	private JComboBox cbIsbn;
	private JButton btnSubmit;
	private JScrollPane spContent;
	/**
	 * Create the panel.
	 */
	private OverdueCheckoutPanel() {
		SystemController ci = new SystemController();
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		
		lHeader = new JLabel("Overdue Checkout");
		lHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
		lHeader.setBounds(10, 10, 430, 20);
		add(lHeader);
		
		lIsbn = new JLabel("ISBN");
		lIsbn.setBounds(75, 44, 45, 13);
		add(lIsbn);
		
		cbIsbn = new JComboBox();
		cbIsbn.setBounds(130, 40, 113, 21);
		List<String> books = ci.allBookIds();
		cbIsbn.setModel(new DefaultComboBoxModel<>(books.toArray(new String[0])));
		add(cbIsbn);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(249, 40, 85, 21);
		add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Response"};
				String records= ci.overDueCheckout(cbIsbn.getSelectedItem().toString());
				System.out.println(records);
			    String[] data = records.split("\n");
			    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			    
				int counter =1;
				  for (String line : data) {
					  Object[] row = {line};  
				        model.addRow(row);
				        if(counter==3) {
				        	 model.addRow(new   Object[] {});
				        	 counter=0;
				        }
				        counter++;
				 }
				 
			    			    tContent.setModel(model);
			  


			 
			//	model.setValueAt(), 0, 0);
	               // model.setValueAt(35, 0, 1);
	                // update the table to reflect the changes in the model
	                tContent.repaint();
			}
		});
		
		
		spContent = new JScrollPane();
		spContent.setBounds(10, 73, 430, 217);
		add(spContent);
		
		tContent = new JTable();
		spContent.setViewportView(tContent);
	}

	public void init() {
		try {
			OverdueCheckoutPanel panel = new OverdueCheckoutPanel();
			panel.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
