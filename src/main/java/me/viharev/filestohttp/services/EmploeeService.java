package me.viharev.filestohttp.services;

import me.viharev.filestohttp.models.Emploee;

import java.util.Map;

public interface EmploeeService {
    void addEmploee(Emploee emploee);

    Map<Long, Map<Long, Emploee>> getAllEmploee();
}
