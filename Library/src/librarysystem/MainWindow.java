package librarysystem;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements LibWindow{
    public static final MainWindow INSTANCE = new MainWindow();
    private boolean isInitialized = false;

    private JPanel topPanel;
    private JMenuBar menuBar;

    private JMenu librarianMenu;
    private JMenuItem checkoutBookItem;
    private JMenuItem checkoutRecordItem;
    private JMenuItem overdueCheckoutItem;
    private JMenuItem allMemberItem;
    private JMenuItem allBookItem;

    private JMenu adminMenu;
    private JMenuItem addMemberItem;
    private JMenuItem editMemberItem;
    private JMenuItem addBookItem;
    private JMenuItem addBookCopyItem;

    private MainWindow() {}

    @Override
    public void init() {
        this.setSize(700,500);
        this.setLayout(new BorderLayout(50,30));
        defineMenuBar();
        setJMenuBar(menuBar);
        this.add(topPanel,BorderLayout.NORTH);
        this.setVisible(true);
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void defineMenuBar() {
        topPanel = new JPanel();
        menuBar = new JMenuBar();
        defineLibMenu();
        defineAdminMenu();
        topPanel.add(menuBar);
    }

    public void defineLibMenu() {
        librarianMenu = new JMenu("LIBRARIAN");
        checkoutBookItem = new JMenuItem("Checkout Book");
        checkoutRecordItem = new JMenuItem("Checkout Record");
        overdueCheckoutItem = new JMenuItem("Overdue Checkout");
        allMemberItem = new JMenuItem("All Member");
        allBookItem = new JMenuItem("All Book");

        librarianMenu.add(checkoutBookItem);
        librarianMenu.add(checkoutRecordItem);
        librarianMenu.add(overdueCheckoutItem);
        librarianMenu.add(allMemberItem);
        librarianMenu.add(allBookItem);

        menuBar.add(librarianMenu);
    }

    public void defineAdminMenu() {
        adminMenu = new JMenu("ADMIN");
        addMemberItem = new JMenuItem("Add Member");
        editMemberItem = new JMenuItem("Edit Member");
        addBookItem = new JMenuItem("Add Book");
        addBookCopyItem = new JMenuItem("Add Book Copy");

        adminMenu.add(addMemberItem);
        adminMenu.add(editMemberItem);
        adminMenu.add(addBookItem);
        adminMenu.add(addBookCopyItem);

        menuBar.add(adminMenu);
    }
}
