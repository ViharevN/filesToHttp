package me.viharev.filestohttp.services;

public interface BookFileService {
    boolean saveToFile(String json);

    String readFromFile();
}
