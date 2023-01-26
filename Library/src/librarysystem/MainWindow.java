package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataaccess.Auth;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {
	
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
		libMenu.add(checkoutBookItem);
		
		checkoutRecordItem = new JMenuItem("Checkout Record");
		libMenu.add(checkoutRecordItem);
		
		overdueItem = new JMenuItem("Overdue Checkout");
		libMenu.add(overdueItem);
		
		alBookItem = new JMenuItem("All Book");
		libMenu.add(alBookItem);
		
		adminMenu = new JMenu("Admin");
		menuBar.add(adminMenu);
		
		addMemberItem = new JMenuItem("Add Member");
		adminMenu.add(addMemberItem);
		
		editMemberItem = new JMenuItem("Edit Member");
		adminMenu.add(editMemberItem);
		
		addBookItem = new JMenuItem("Add Book");
		adminMenu.add(addBookItem);
		
		addCopyItem = new JMenuItem("Add Copy Book");
		adminMenu.add(addCopyItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pContent = new JPanel();
		pContent.setBounds(10, 10, 543, 318);
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
			} else if(auth.equals(Auth.ADMIN)) {
				frame.libMenu.setVisible(false);
			}
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
