package com.example.agenciaNueva.controller;

import com.example.agenciaNueva.dto.HotelDTO;
import com.example.agenciaNueva.dto.VueloDTO;
import com.example.agenciaNueva.service.IHotelService;
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
@RequestMapping("/hoteles")
public class HotelController {

    private final IHotelService hotelService;

    public HotelController(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(
            summary = "Creamos un nuevo hotel",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"nombre\": \"Hotel Ejemplo\",\n" +
                                            "  \"lugar\": \"Madrid\",\n" +
                                            "  \"tipoHabitacion\": \"Doble\",\n" +
                                            "  \"precioHabitacion\": 100,\n" +
                                            "  \"fechaDesde\": \"2025-02-18\",\n" +
                                            "  \"fechaHasta\": \"2025-02-25\",\n" +
                                            "  \"estaReservado\": false\n" +
                                            "}"
                            )
                    )
            )
    )
    @PostMapping("/nuevo")
    public ResponseEntity<String> crear(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.guardar(hotelDTO));
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> obtenerTodos(){
        return ResponseEntity.ok(hotelService.buscarTodos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<HotelDTO>> obtenerDisponibles(
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo) {

        List<HotelDTO> hotelesDisponibles = hotelService.buscarDisponibles(destination, dateFrom, dateTo);

        return hotelesDisponibles.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(hotelesDisponibles)
                : ResponseEntity.ok(hotelesDisponibles);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String response = hotelService.eliminar(id);
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
            summary = "Actualizamos el hotel",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"nombre\": \"Hotel Ejemplo\",\n" +
                                            "  \"lugar\": \"Madrid\",\n" +
                                            "  \"tipoHabitacion\": \"Doble\",\n" +
                                            "  \"precioHabitacion\": 100,\n" +
                                            "  \"fechaDesde\": \"2025-02-18\",\n" +
                                            "  \"fechaHasta\": \"2025-02-25\",\n" +
                                            "  \"estaReservado\": false\n" +
                                            "}"
                            )
                    )
            )
    )
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody HotelDTO hotelDTO) {
        String response = hotelService.actualizar(id,hotelDTO);
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
