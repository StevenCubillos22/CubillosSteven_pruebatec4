package com.example.agenciaNueva.controller;

import com.example.agenciaNueva.dto.VueloDTO;
import com.example.agenciaNueva.service.IVueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    private final IVueloService vueloService;

    public VueloController(IVueloService vueloService) {
        this.vueloService = vueloService;
    }

    @Operation(
            summary = "Crear un nuevo vuelo",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"aerolinea\": \"Aerolínea Ejemplo\",\n" +
                                            "  \"origen\": \"Buenos Aires\",\n" +
                                            "  \"destino\": \"Madrid\",\n" +
                                            "  \"fechaSalida\": \"2025-02-18\",\n" +
                                            "  \"fechaVuelta\": \"2025-02-25\",\n" +
                                            "  \"asientos\": 150,\n" +
                                            "  \"precioPorPersona\": 500\n" +
                                            "}"
                            )
                    )
            )
    )
    @PostMapping("/nuevo")
    public ResponseEntity<String> crear(@RequestBody VueloDTO vueloDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vueloService.guardar(vueloDTO));
    }

    @GetMapping
    public ResponseEntity<List<VueloDTO>> obtenerTodos(){
        return ResponseEntity.ok(vueloService.buscarTodos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<VueloDTO>> obtenerDisponibles(
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo) {

        List<VueloDTO> vuelosDisponibles = vueloService.buscarDisponibles(destination, dateFrom, dateTo);

        return vuelosDisponibles.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(vuelosDisponibles)
                : ResponseEntity.ok(vuelosDisponibles);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String response = vueloService.eliminar(id);
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
            summary = "Actualizamos vuelo",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"aerolinea\": \"Aerolínea Ejemplo modificado\",\n" +
                                            "  \"origen\": \"Buenos Aires\",\n" +
                                            "  \"destino\": \"Madrid\",\n" +
                                            "  \"fechaSalida\": \"2025-02-18\",\n" +
                                            "  \"fechaVuelta\": \"2025-02-25\",\n" +
                                            "  \"asientos\": 150,\n" +
                                            "  \"precioPorPersona\": 500\n" +
                                            "}"
                            )
                    )
            )
    )
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody VueloDTO vueloDTO) {
        String response = vueloService.actualizar(id, vueloDTO);
        if (response.equals("Se actualizó con éxito.")) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
    }
}
