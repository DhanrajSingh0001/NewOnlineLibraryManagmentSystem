package dao;

import model.Book;
import java.util.*;

public class BookDAO {
    private Map<Integer, Book> books = new HashMap<>();
    private int nextId = 1;

    public synchronized Book addBook(String title, String author, String publisher, int quantity) {
        Book book = new Book(nextId++, title, author, publisher, quantity);
        books.put(book.getBookId(), book);
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Book findBookById(int id) {
        return books.get(id);
    }
}

