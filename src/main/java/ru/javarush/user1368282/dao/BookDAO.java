package ru.javarush.user1368282.dao;

import ru.javarush.user1368282.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookDAO {
    long createBook(Book book);

    Book updateBook(Book book);

    void deleteBook(long id);

    List<Book> getAllBooks(int page, int perPage);

    List<Book> getAllBooks(int page, int perPage, Map<String, Object> search);

    Book getBook(long id);

    long getTotalBooks();

    long getTotalBooks(Map<String, Object> search);
}
