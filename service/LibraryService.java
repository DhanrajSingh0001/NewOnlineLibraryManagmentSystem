package service;

import dao.BookDAO;
import dao.TransactionDAO;
import model.Book;
import model.Transaction;
import model.User;

import java.util.Date;

public class LibraryService {
    private BookDAO bookDAO;
    private TransactionDAO transactionDAO;

    public LibraryService(BookDAO bookDAO, TransactionDAO transactionDAO) {
        this.bookDAO = bookDAO;
        this.transactionDAO = transactionDAO;
    }

    // Borrow book
    public synchronized Transaction borrowBook(Book book, User user) {
        if (book.getQuantity() <= 0) return null;
        book.setQuantity(book.getQuantity() - 1);
        Transaction transaction = new Transaction(
                transactionDAO.getAllTransactions().size() + 1,
                book, user, new Date()
        );
        transactionDAO.addTransaction(transaction);
        return transaction;
    }

    // Return book
    public synchronized void returnBook(Transaction transaction) {
        transaction.setReturnDate(new Date());
        Book book = transaction.getBook();
        book.setQuantity(book.getQuantity() + 1);
    }
}

