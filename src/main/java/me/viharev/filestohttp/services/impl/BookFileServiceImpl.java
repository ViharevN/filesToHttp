package me.viharev.filestohttp.services.impl;

import me.viharev.filestohttp.services.BookFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BookFileServiceImpl implements BookFileService {
    @Value("${path.to.file.book.json}")
    private String pathToBookJson;
    @Value("${name.of.file.book.json}")
    private String nameOfBookJson;
    @Override
    public boolean saveToFile(String json) {
        cleanToFile();
        try {
            Files.writeString(Path.of(pathToBookJson, nameOfBookJson), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readFromFile() {
        try {
           return Files.readString(Path.of(pathToBookJson, nameOfBookJson));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean cleanToFile() {
        try {
            Files.deleteIfExists(Path.of(pathToBookJson, nameOfBookJson));
            Files.createFile(Path.of(pathToBookJson, nameOfBookJson));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
