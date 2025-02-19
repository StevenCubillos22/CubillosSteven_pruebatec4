package com.example.agenciaNueva.controller;


import com.example.agenciaNueva.dto.PersonaDTO;
import com.example.agenciaNueva.service.IPersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final IPersonaService personaService;

    public PersonaController(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @Operation(
            summary = "Creamos una nueva persona",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"nombre\": \"Alvaro\",\n" +
                                            "  \"apellido\": \"Majo\"\n" +
                                            "}"
                            )
                    )
            )
    )
    @PostMapping("/nuevo")
    public ResponseEntity<String> crear(@RequestBody PersonaDTO persona) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.guardar(persona));
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> obtenerTodos(){
        return ResponseEntity.ok(personaService.buscarTodos());
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String response = personaService.eliminar(id);
        if (response.equals("Se eliminó con éxito.")) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
    }

    @Operation(
            summary = "Actualizamos una nueva persona",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"nombre\": \"Alvaro\",\n" +
                                            "  \"apellido\": \"Majo\"\n" +
                                            "}"
                            )
                    )
            )
    )
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody PersonaDTO paciente) {

        try{
            String response = personaService.actualizarForma2(id,paciente);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
