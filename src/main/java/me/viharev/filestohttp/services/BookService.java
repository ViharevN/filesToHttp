package me.viharev.filestohttp.services;

import me.viharev.filestohttp.models.Books;

import java.util.Map;

public interface BookService {
    void addBook(Books books);

    Map<Long, Books> getBooks();
}
