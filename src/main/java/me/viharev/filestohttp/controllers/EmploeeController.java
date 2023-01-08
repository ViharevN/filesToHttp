package me.viharev.filestohttp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.viharev.filestohttp.services.EmploeeService;
import me.viharev.filestohttp.models.Emploee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/emploee")
@Tag(
        name = "Emploee Controller",
        description = "CRUD-операции с работниками"
)
public class EmploeeController {
    private EmploeeService emploeeService;

    public EmploeeController(EmploeeService emploeeService) {
        this.emploeeService = emploeeService;
    }

    @PostMapping("/add")
    @Operation(
            summary = "Добавление работника в Карту",
            description = "Добавляем работника с помощью POST"
    )
    public ResponseEntity<Emploee> addEmploee(@RequestBody Emploee emploee) {
        emploeeService.addEmploee(emploee);
        return ResponseEntity.ok(emploee);
    }

    @GetMapping("/get")
    @Operation(
            summary = "Возвращаем карту с работниками со всеми",
            description = "Карта с работниками, сортируем по месяцам"
    )
    public Map<Long, Map<Long, Emploee>> getAllEmploees() {
       return emploeeService.getAllEmploee();
    }
}
