package me.viharev.filestohttp.services;

public interface EmploeeFileService {
    boolean saveToFile(String json);

    String readOfFile();
}
