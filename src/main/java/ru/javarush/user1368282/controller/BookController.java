package ru.javarush.user1368282.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ru.javarush.user1368282.entity.Book;
import ru.javarush.user1368282.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @PutMapping("/{id}")
    public String changeBookEdition(@ModelAttribute Book book) {
        bookService.changeBookEdition(book, book.getTitle(), book.getDescription(), book.getIsbn(), book.getYear());
        return "redirect:/";
    }

    @PutMapping("/{id}/read")
    public String markBookAsRead(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        bookService.markBookAsRead(book);
        return "redirect:/";
    }

    @GetMapping("/")
    public String listAllBooks(Model model, WebRequest request) {
        Map<String, Object> modelMap = model.asMap();
        Map<String, Object> search = (Map<String, Object>) modelMap.get("search");
        int page = (int) modelMap.get("page");
        int perPage = (int) modelMap.get("perPage");
        long totalBooks = bookService.getTotalBooks(search);
        model.addAttribute("totalPages", totalBooks / perPage);
        model.addAttribute("totalBooks", totalBooks);
        List<Book> bookList = bookService.getAllBooks(page, perPage, search);
        model.addAttribute("books", bookList);
        return "index";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable Long id) {
        model.addAttribute("message", "show book with id " + id);
        model.addAttribute("book", bookService.getBook(id));
        return "bookForm";
    }

    @GetMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        return "bookForm";
    }

    @PostMapping("/")
    public String createNewBook(@ModelAttribute Book book) {
        bookService.createBook(book);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(Model model, @PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/";
    }

    @ModelAttribute
    private void pageAttribute(WebRequest request, Model model) {
        String pageParam = request.getParameter("page");
        int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
        page = page < 1 ? 1 : page;
        model.addAttribute("page", page);
    }

    @ModelAttribute
    private void searchAttribute(WebRequest request, Model model) {
        Map<String, Object> search = new HashMap<>();
        if (request.getParameter("search") != null) {
            search.put("title", request.getParameter("search"));
        }
        model.addAttribute("search", search);
    }

    @ModelAttribute
    private void perPageAttribute(WebRequest request, Model model) {
        String perPageParam = request.getParameter("perPage");
        int perPage = perPageParam == null ? 10 : Integer.parseInt(perPageParam);
        perPage = perPage < 0 ? 10 : perPage;
        model.addAttribute("perPage", perPage);
    }
}