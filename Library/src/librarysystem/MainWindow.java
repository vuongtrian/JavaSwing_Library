package librarysystem;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataaccess.Auth;
import librarysystem.adminpanel.AddBookCopyPanel;
import librarysystem.adminpanel.AddBookPanel;
import librarysystem.adminpanel.AddMemberPanel;
import librarysystem.adminpanel.EditMemberPanel;
import librarysystem.libpanel.CheckoutBookPanel;
import librarysystem.libpanel.CheckoutRecordPanel;
import librarysystem.libpanel.OverdueCheckoutPanel;

import javax.swing.JButton;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MainWindow extends JFrame{
	
	public static final MainWindow INSTANCE = new MainWindow();
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow frame = new MainWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	private JMenuBar menuBar;
	private JMenu libMenu;
	private JMenuItem checkoutBookItem;
	private JMenuItem checkoutRecordItem;
	private JMenuItem overdueItem;
	private JMenuItem alBookItem;
	private JMenu adminMenu;
	private JMenuItem addMemberItem;
	private JMenuItem editMemberItem;
	private JMenuItem addBookItem;
	private JMenuItem addCopyItem;
	private JPanel pContent;
	private JButton btnLogout;
	private JButton btnNewButton;

	private MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 428);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		libMenu = new JMenu("Librarian");
		menuBar.add(libMenu);
		
		checkoutBookItem = new JMenuItem("Checkout Book");
//		checkoutRecordItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				pContent.getComponent(0).setVisible(true);
//				pContent.getComponent(1).setVisible(false);
//				pContent.getComponent(2).setVisible(false);
//				pContent.getComponent(3).setVisible(false);
//				pContent.getComponent(4).setVisible(false);
//				pContent.getComponent(5).setVisible(false);
//				pContent.getComponent(6).setVisible(false);
//			}
//		});
		libMenu.add(checkoutBookItem);
		
		checkoutRecordItem = new JMenuItem("Checkout Record");
		checkoutRecordItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pContent.getComponent(0).setVisible(false);
				pContent.getComponent(1).setVisible(true);
				pContent.getComponent(2).setVisible(false);
				pContent.getComponent(3).setVisible(false);
				pContent.getComponent(4).setVisible(false);
				pContent.getComponent(5).setVisible(false);
				pContent.getComponent(6).setVisible(false);
			}
		});
		libMenu.add(checkoutRecordItem);
		
		overdueItem = new JMenuItem("Overdue Checkout");
		overdueItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pContent.getComponent(0).setVisible(false);
				pContent.getComponent(1).setVisible(false);
				pContent.getComponent(2).setVisible(true);
				pContent.getComponent(3).setVisible(false);
				pContent.getComponent(4).setVisible(false);
				pContent.getComponent(5).setVisible(false);
				pContent.getComponent(6).setVisible(false);
			}
		});
		libMenu.add(overdueItem);
		
//		alBookItem = new JMenuItem("All Book");
//		alBookItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				pContent.getComponent(0).setVisible(false);
//				pContent.getComponent(1).setVisible(false);
//				pContent.getComponent(2).setVisible(false);
//				pContent.getComponent(3).setVisible(true);
//				pContent.getComponent(4).setVisible(false);
//				pContent.getComponent(5).setVisible(false);
//				pContent.getComponent(6).setVisible(false);
//			}
//		});
//		libMenu.add(alBookItem);
		
		adminMenu = new JMenu("Admin");
		menuBar.add(adminMenu);
		
		addMemberItem = new JMenuItem("Add Member");
		addMemberItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pContent.getComponent(0).setVisible(false);
				pContent.getComponent(1).setVisible(false);
				pContent.getComponent(2).setVisible(false);
				pContent.getComponent(3).setVisible(false);
				pContent.getComponent(4).setVisible(false);
				pContent.getComponent(5).setVisible(true);
				pContent.getComponent(6).setVisible(false);
			}
		});
		adminMenu.add(addMemberItem);
		
		editMemberItem = new JMenuItem("Edit Member");
		editMemberItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pContent.getComponent(0).setVisible(false);
				pContent.getComponent(1).setVisible(false);
				pContent.getComponent(2).setVisible(false);
				pContent.getComponent(3).setVisible(false);
				pContent.getComponent(4).setVisible(false);
				pContent.getComponent(5).setVisible(false);
				pContent.getComponent(6).setVisible(true);
			}
		});
		adminMenu.add(editMemberItem);
		
		addBookItem = new JMenuItem("Add Book");
		addBookItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pContent.getComponent(0).setVisible(false);
				pContent.getComponent(1).setVisible(false);
				pContent.getComponent(2).setVisible(false);
				pContent.getComponent(3).setVisible(true);
				pContent.getComponent(4).setVisible(false);
				pContent.getComponent(5).setVisible(false);
				pContent.getComponent(6).setVisible(false);
			}
		});
		adminMenu.add(addBookItem);
		
		addCopyItem = new JMenuItem("Add Copy Book");
		addCopyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pContent.getComponent(0).setVisible(false);
				pContent.getComponent(1).setVisible(false);
				pContent.getComponent(2).setVisible(false);
				pContent.getComponent(3).setVisible(false);
				pContent.getComponent(4).setVisible(true);
				pContent.getComponent(5).setVisible(false);
				pContent.getComponent(6).setVisible(false);
			}
		});
		adminMenu.add(addCopyItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pContent = new JPanel();
		pContent.setBounds(10, 10, 543, 318);
		setContent();
		contentPane.add(pContent);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(468, 338, 85, 21);
		contentPane.add(btnLogout);
		
		btnNewButton = new JButton("All Members");
		btnNewButton.setBounds(10, 338, 89, 21);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});


	}

	public void init(Auth auth) {
		try {
			MainWindow frame = new MainWindow();
			if(auth.equals(Auth.LIBRARIAN)) {
				frame.adminMenu.setVisible(false);
//				frame.pContent.add(CheckoutBookPanel.INSTANCE);
				frame.pContent.getComponent(0).setVisible(true);
			} else if(auth.equals(Auth.ADMIN)) {
				frame.libMenu.setVisible(false);
//				frame.pContent.add(AddMemberPanel.INSTANCE);
//				frame.pContent.getComponent(5).setVisible(true);
			} else {
//				frame.pContent.add(CheckoutBookPanel.INSTANCE);
				frame.pContent.getComponent(0).setVisible(true);
			}
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setContent() {
		pContent.add(CheckoutBookPanel.INSTANCE);
		pContent.add(CheckoutRecordPanel.INSTANCE);
		pContent.add(OverdueCheckoutPanel.INSTANCE);
		pContent.add(AddBookPanel.INSTANCE);
		pContent.add(AddBookCopyPanel.INSTANCE);
		pContent.add(AddMemberPanel.INSTANCE);
		pContent.add(EditMemberPanel.INSTANCE);
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		MainWindow frame = new MainWindow();
//		if(e.getSource() == checkoutBookItem) {
//			frame.pContent.add(CheckoutBookPanel.INSTANCE);
//		} else if(e.getSource() == checkoutRecordItem) {
//			System.out.println("CheckoutRecordItem");
//			frame.pContent.add(CheckoutRecordPanel.INSTANCE);
//		} else if(e.getSource() == overdueItem) {
//			frame.pContent.add(OverdueCheckoutPanel.INSTANCE);
//		} else if(e.getSource() == addMemberItem) {
//			frame.pContent.add(AddMemberPanel.INSTANCE);
//		} else if(e.getSource() == editMemberItem) {
//			frame.pContent.add(EditMemberPanel.INSTANCE);
//		} else if(e.getSource() == addBookItem) {
//			frame.pContent.add(AddBookPanel.INSTANCE);
//		} else if(e.getSource() == addCopyItem) {
//			frame.pContent.add(AddBookCopyPanel.INSTANCE);
//		}
//	}
}
