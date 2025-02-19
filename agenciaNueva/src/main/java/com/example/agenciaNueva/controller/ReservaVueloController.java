package com.example.agenciaNueva.controller;

import com.example.agenciaNueva.dto.ReservaVueloDTO;
import com.example.agenciaNueva.service.IReservaVueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva-vuelo")
public class ReservaVueloController {

    private final IReservaVueloService reservaVueloService;

    public ReservaVueloController(IReservaVueloService reservaVueloService) {
        this.reservaVueloService = reservaVueloService;
    }

    @Operation(
            summary = "Crear una reserva de vuelo",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"fechaReserva\": \"2025-02-18\",\n" +
                                            "  \"origen\": \"Buenos Aires\",\n" +
                                            "  \"destino\": \"Madrid\",\n" +
                                            "  \"cantidadPersonas\": 2,\n" +
                                            "  \"tipoAsiento\": \"Economy\",\n" +
                                            "  \"persona\": {\n" +
                                            "    \"id\": 1\n" +
                                            "  },\n" +
                                            "  \"vuelo\": {\n" +
                                            "    \"id\": 1\n" +
                                            "  }\n" +
                                            "}"
                            )
                    )
            )
    )

    @PostMapping("/nuevo")
    public ResponseEntity<String> crear(@RequestBody ReservaVueloDTO reservaVueloDTO) {
        String response = reservaVueloService.guardar(reservaVueloDTO);
        if (response.equals("Se creó con éxito.")) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservaVueloDTO>> obtenerTodos(){
        return ResponseEntity.ok(reservaVueloService.buscarTodos());
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String response = reservaVueloService.eliminar(id);
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
}
