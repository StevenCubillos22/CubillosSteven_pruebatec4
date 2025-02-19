package com.example.agenciaNueva.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VueloDTO {

    private Integer id;
    private String aerolinea;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalDate fechaVuelta;
    private int asientos;
    private Double precioPorPersona;
}
