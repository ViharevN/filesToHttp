package me.viharev.filestohttp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.viharev.filestohttp.models.Books;
import me.viharev.filestohttp.services.BookFileService;
import me.viharev.filestohttp.services.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    private BookFileService bookFileService;
    private static long counterBook;
    private Map<Long, Books> booksMap = new LinkedHashMap<>();

    public BookServiceImpl(BookFileService bookFileService) {
        this.bookFileService = bookFileService;
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public void addBook(Books books) {
        booksMap.put(counterBook, books);
        counterBook++;
        saveToFile();
    }
    @Override
    public Map<Long, Books> getBooks() {
        readFromFile();
        return booksMap;
    }
    private void readFromFile() {
        String json = bookFileService.readFromFile();
        try {
            booksMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Books>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(booksMap);
            bookFileService.saveToFile(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
