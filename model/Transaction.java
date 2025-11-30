package model;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private Book book;
    private User user;
    private Date borrowDate;
    private Date returnDate;

    public Transaction(int transactionId, Book book, User user, Date borrowDate) {
        this.transactionId = transactionId;
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    public int getTransactionId() { return transactionId; }
    public Book getBook() { return book; }
    public User getUser() { return user; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getReturnDate() { return returnDate; }

    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return book.getTitle() + " borrowed by " + user.getName() +
                " on " + borrowDate +
                (returnDate == null ? " (Not returned)" : " (Returned on " + returnDate + ")");
    }
}

