package librarysystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 428);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu libMenu = new JMenu("Librarian");
		menuBar.add(libMenu);
		
		JMenuItem checkoutBookItem = new JMenuItem("Checkout Book");
		libMenu.add(checkoutBookItem);
		
		JMenuItem checkoutRecordItem = new JMenuItem("Checkout Record");
		libMenu.add(checkoutRecordItem);
		
		JMenuItem overdueItem = new JMenuItem("Overdue Checkout");
		libMenu.add(overdueItem);
		
		JMenuItem alBookItem = new JMenuItem("All Book");
		libMenu.add(alBookItem);
		
		JMenu adminMenu = new JMenu("Admin");
		menuBar.add(adminMenu);
		
		JMenuItem addMemberItem = new JMenuItem("Add Member");
		adminMenu.add(addMemberItem);
		
		JMenuItem editMemberItem = new JMenuItem("Edit Member");
		adminMenu.add(editMemberItem);
		
		JMenuItem addBookItem = new JMenuItem("Add Book");
		adminMenu.add(addBookItem);
		
		JMenuItem addCopyItem = new JMenuItem("Add Copy Book");
		adminMenu.add(addCopyItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pContent = new JPanel();
		pContent.setBounds(10, 10, 543, 318);
		contentPane.add(pContent);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(468, 338, 85, 21);
		contentPane.add(btnLogout);
		
		JButton btnNewButton = new JButton("All Members");
		btnNewButton.setBounds(10, 338, 89, 21);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
