package librarysystem.libpanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
				String[] columnNames = {"Response"};
			    String[] data = ci.makeCheckout(tfId.getSelectedItem().toString(), cbIsbn.getSelectedItem().toString()).split("\n");
			    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			    			  

				  for (String line : data) {
					  Object[] row = {line};  
				        model.addRow(row);
				 }
				    tContent.setModel(model);

			 
			//	model.setValueAt(), 0, 0);
	               // model.setValueAt(35, 0, 1);
	                // update the table to reflect the changes in the model
	                tContent.repaint();
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
		  tContent.setDefaultRenderer(String.class, new TextAreaRenderer());
		    tContent.setDefaultEditor(String.class, new TextAreaEditor());
		spContent.setViewportView(tContent);
	}

	public CheckoutBookPanel init() {
		CheckoutBookPanel panel;
		panel = new CheckoutBookPanel();
		panel.setVisible(true);
		return panel;
	}
	class TextAreaRenderer extends JTextArea implements TableCellRenderer {
	    public TextAreaRenderer() {
	        setLineWrap(true);
	        setWrapStyleWord(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        setText((String) value);
	        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
	        if (table.getRowHeight(row) != getPreferredSize().height) {
	            table.setRowHeight(row, getPreferredSize().height);
	        }
	        return this;
	    }
	}
	class TextAreaEditor extends DefaultCellEditor {
	    protected JTextArea textArea;

	    public TextAreaEditor() {
	        super(new JTextField());
	        textArea = new JTextArea();
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setBorder(null);
	    }

	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	        textArea.setText((String) value);
	        return textArea;
	    }

	    public Object getCellEditorValue() {
	        return textArea.getText();
	    }
	}

}
