package ru.javarush.user1368282.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.user1368282.dao.BookDAO;
import ru.javarush.user1368282.entity.Book;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public long createBook(Book book) {
        return bookDAO.createBook(book);
    }

    @Override
    public void markBookAsRead(Book book) {
        if (book == null) {
            return;
        }
        book.setRead(true);
        bookDAO.updateBook(book);
    }

    @Override
    public void changeBookEdition(Book book, String title, String description, String isbn, Integer year) {
        if (book == null) {
            return;
        }

        if (title != null) {
            book.setTitle(title);
        }

        if (description != null) {
            book.setDescription(description);
        }

        if (isbn != null) {
            book.setIsbn(isbn);
        }

        if (year != null) {
            book.setYear(year);
        }

        book.setRead(false);
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(long id) {
        bookDAO.deleteBook(id);
    }

    @Override
    public Book getBook(long id) {
        return bookDAO.getBook(id);
    }

    @Override
    public long getTotalBooks() {
        return bookDAO.getTotalBooks();
    }

    @Override
    public long getTotalBooks(Map<String, Object> search) {
        return bookDAO.getTotalBooks(search);
    }

    @Override
    public List<Book> getAllBooks(int page, int perPage) {
        return bookDAO.getAllBooks(page, perPage);
    }

    @Override
    public List<Book> getAllBooks(int page, int perPage, Map<String, Object> search) {
        return bookDAO.getAllBooks(page, perPage, search);
    }
}
