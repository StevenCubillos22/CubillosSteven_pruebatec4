package com.example.agenciaNueva.controller;

import com.example.agenciaNueva.dto.ReservaHotelDTO;
import com.example.agenciaNueva.dto.ReservaVueloDTO;
import com.example.agenciaNueva.service.IReservaHotelService;
import com.example.agenciaNueva.service.IReservaVueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva-hotel")
public class ReservaHotelController {

    private final IReservaHotelService reservaHotelService;

    public ReservaHotelController(IReservaHotelService reservaHotelService) {
        this.reservaHotelService = reservaHotelService;
    }

    @Operation(summary = "Crear una reserva de hotel",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"checkIn\": \"2025-02-20\",\n" +
                                            "  \"checkOut\": \"2025-02-25\",\n" +
                                            "  \"noches\": 5,\n" +
                                            "  \"ciudad\": \"Madrid\",\n" +
                                            "  \"cantPersonas\": 2,\n" +
                                            "  \"tipoHabitacion\": \"Doble\",\n" +
                                            "  \"persona\": {\n" +
                                            "    \"id\": 1\n" +
                                            "  },\n" +
                                            "  \"hotel\": {\n" +
                                            "    \"id\": 1\n" +
                                            "  }\n" +
                                            "}"
                            )
                    )
            )
    )

    @PostMapping("/crear")
    public ResponseEntity<String> crear(@RequestBody ReservaHotelDTO reservaHotelDTO) {
        String response = reservaHotelService.guardar(reservaHotelDTO);
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
    public ResponseEntity<List<ReservaHotelDTO>> obtenerTodos(){
        return ResponseEntity.ok(reservaHotelService.buscarTodos());
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String response = reservaHotelService.eliminar(id);
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
