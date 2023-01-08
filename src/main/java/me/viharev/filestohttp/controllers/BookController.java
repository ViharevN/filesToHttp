package me.viharev.filestohttp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.viharev.filestohttp.services.BookService;
import me.viharev.filestohttp.models.Books;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Books Controller")
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Books books) {
        bookService.addBook(books);
    }

    @GetMapping("/get")
    public Map<Long, Books> getBooks() {
       return bookService.getBooks();
    }
}
