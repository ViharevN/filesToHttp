package me.viharev.filestohttp.services.impl;

import me.viharev.filestohttp.services.EmploeeFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmploeeFileServiceImpl implements EmploeeFileService {
    @Value("${path.to.file.emploee.json}")
    private String pathToEmploee;
    @Value("${name.of.file.emploee.json}")
    private String nameOfEmploee;

    @Override
    public boolean saveToFile(String json) {
        cleanDataFile();
        try {
            Files.writeString(Path.of(pathToEmploee, nameOfEmploee), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readOfFile() {
        try {
            return Files.readString(Path.of(pathToEmploee, nameOfEmploee));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void cleanDataFile() {
        try {
            Files.deleteIfExists(Path.of(pathToEmploee, nameOfEmploee));
            Files.createFile(Path.of(pathToEmploee, nameOfEmploee));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
