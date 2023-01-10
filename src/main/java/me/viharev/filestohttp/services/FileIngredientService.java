package me.viharev.filestohttp.services;

import java.io.File;

public interface FileIngredientService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    void cleanToFile();
}
