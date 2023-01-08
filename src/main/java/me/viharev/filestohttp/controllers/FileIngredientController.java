package me.viharev.filestohttp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.viharev.filestohttp.services.FileIngredientService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/ingredient")
@Tag(
        name = "Контроллер ингредиентов",
        description = "Импорт/экспорт файлов ингредиентов"
)
public class FileIngredientController {
    private FileIngredientService fileIngredientService;

    public FileIngredientController(FileIngredientService fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }

    @GetMapping(value = "/export")
    @Operation(
            summary = "Экспорт файла с ингредиентами"
    )
    public ResponseEntity<InputStreamResource> downloadIngredientFile() {
        File file = fileIngredientService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = null;
            try {
                resource = new InputStreamResource(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ingredients.json\"")
                    .body(resource);

        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Импорт ингредиентов",
            description = "Импортируем ингредиенты из файла"
    )
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        fileIngredientService.cleanToFile();
        File dataFile = fileIngredientService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
