package gui;


import dao.BookDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import model.Book;
import model.User;
import model.Transaction;
import service.LibraryService;

import javax.swing.*;
import java.awt.BorderLayout;


 public class MainFrame extends JFrame {
    private BookDAO bookDAO = new BookDAO();
    private UserDAO userDAO = new UserDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
    private LibraryService service = new LibraryService(bookDAO, transactionDAO);

    private DefaultListModel<Book> bookListModel = new DefaultListModel<>();
    private DefaultListModel<User> userListModel = new DefaultListModel<>();
    private DefaultListModel<Transaction> transactionListModel = new DefaultListModel<>();

    public MainFrame() {
        setTitle("In-Memory Library System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panels
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Books", createBookPanel());
        tabs.add("Users", createUserPanel());
        tabs.add("Transactions", createTransactionPanel());

        add(tabs);
        setVisible(true);
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JList<Book> bookList = new JList<>(bookListModel);
        JButton addBookBtn = new JButton("Add Book");
        addBookBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Title");
            String author = JOptionPane.showInputDialog("Author");
            String publisher = JOptionPane.showInputDialog("Publisher");
            int qty = Integer.parseInt(JOptionPane.showInputDialog("Quantity"));
            Book book = bookDAO.addBook(title, author, publisher, qty);
            bookListModel.addElement(book);
        });

        panel.add(new JScrollPane(bookList), BorderLayout.CENTER);
        panel.add(addBookBtn, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JList<User> userList = new JList<>(userListModel);
        JButton addUserBtn = new JButton("Add User");
        addUserBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Name");
            String role = JOptionPane.showInputDialog("Role (admin/user)");
            User user = userDAO.addUser(name, role);
            userListModel.addElement(user);
        });

        panel.add(new JScrollPane(userList), BorderLayout.CENTER);
        panel.add(addUserBtn, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createTransactionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JList<Transaction> transactionList = new JList<>(transactionListModel);
        JButton borrowBtn = new JButton("Borrow Book");

        borrowBtn.addActionListener(e -> {
            Book book = bookListModel.getSize() > 0 ? bookListModel.getElementAt(0) : null;
            User user = userListModel.getSize() > 0 ? userListModel.getElementAt(0) : null;
            if (book != null && user != null) {
                Transaction t = service.borrowBook(book, user);
                if (t != null) transactionListModel.addElement(t);
            }
        });

        panel.add(new JScrollPane(transactionList), BorderLayout.CENTER);
        panel.add(borrowBtn, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
