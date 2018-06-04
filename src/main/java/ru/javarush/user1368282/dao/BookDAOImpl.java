package ru.javarush.user1368282.dao;

import org.springframework.stereotype.Repository;
import ru.javarush.user1368282.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public long createBook(Book book) {
        manager.persist(book);
        manager.flush();
        return book.getId();
    }

    @Override
    public Book updateBook(Book book) {
        manager.merge(book);
        manager.flush();
        return book;
    }

    @Override
    public void deleteBook(long id) {
        Book book = getBook(id);
        manager.remove(book);
    }

    @Override
    public Book getBook(long id) {
        return manager.find(Book.class, id);
    }

    @Override
    public long getTotalBooks() {
        return (long) manager.createQuery("SELECT COUNT(*) FROM Book").getSingleResult();
    }

    @Override
    public long getTotalBooks(Map<String, Object> search) {
        if (search == null || search.isEmpty()) {
            return getTotalBooks();
        }

        StringBuilder where = new StringBuilder();
        if (search.containsKey("title")) {
            where.append(" b.title LIKE '%")
                    .append(search.get("title"))
                    .append("%'");
        }
        return (long) manager.createQuery("SELECT COUNT(*) FROM Book b WHERE " +
                where.toString()).getSingleResult();
    }

    @Override
    public List<Book> getAllBooks(int page, int perPage) {
        return manager.createQuery("SELECT b FROM Book b", Book.class)
                .setFirstResult((page - 1) * perPage)
                .setMaxResults(perPage)
                .getResultList();
    }

    @Override
    public List<Book> getAllBooks(int page, int perPage, Map<String, Object> search) {
        if (search == null || search.isEmpty()) {
            return getAllBooks(page, perPage);
        }

        StringBuilder where = new StringBuilder();
        if (search.containsKey("title")) {
            where.append(" b.title LIKE '%")
                    .append(search.get("title"))
                    .append("%'");
        }

        return manager.createQuery("SELECT b FROM Book b WHERE " + where.toString(), Book.class)
                .setMaxResults(perPage)
                .setFirstResult((page - 1) * perPage)
                .getResultList();
    }
}
