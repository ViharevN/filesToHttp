package me.viharev.filestohttp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.viharev.filestohttp.models.Emploee;
import me.viharev.filestohttp.services.EmploeeFileService;
import me.viharev.filestohttp.services.EmploeeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Service
public class EmploeeServiceImpl implements EmploeeService {
    private EmploeeFileService emploeeFileService;
    private static long counterEmploee = 0;
    private static long counter = 0;
    Map<Long, Map<Long, Emploee>> emploeeMap = new TreeMap<>();

    public EmploeeServiceImpl(EmploeeFileService emploeeFileService) {
        this.emploeeFileService = emploeeFileService;
    }
    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public void addEmploee(Emploee emploee) {
        Map<Long, Emploee> emploeeMap1 = new TreeMap<>();
        emploeeMap1.put(counterEmploee, emploee);
        counterEmploee++;
        emploeeMap.put(counter, emploeeMap1);
        counter++;
        saveToFile();
    }
    @Override
    public Map<Long, Map<Long, Emploee>> getAllEmploee() {
        return emploeeMap;
    }

    private void saveToFile() {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(emploeeMap);
            emploeeFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private void readFromFile() {
        String json = emploeeFileService.readOfFile();
        try {
           emploeeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Map<Long, Emploee>>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
