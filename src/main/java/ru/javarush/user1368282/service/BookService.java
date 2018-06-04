package ru.javarush.user1368282.service;

import ru.javarush.user1368282.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    long createBook(Book book);

    void deleteBook(long id);

    void markBookAsRead(Book book);

    void changeBookEdition(Book book, String title, String description, String isbn, Integer year);

    Book getBook(long id);

    List<Book> getAllBooks(int page, int perPage);

    List<Book> getAllBooks(int page, int perPage, Map<String, Object> search);

    long getTotalBooks();

    long getTotalBooks(Map<String, Object> search);
}
