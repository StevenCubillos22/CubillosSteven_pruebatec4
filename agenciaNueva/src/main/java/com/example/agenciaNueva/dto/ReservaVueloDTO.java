package com.example.agenciaNueva.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaVueloDTO {
    private Integer id;
    private LocalDate fechaReserva;
    private String origen;
    private String destino;
    private Integer cantidadPersonas;
    private String tipoAsiento;
    private PersonaDTO persona;
    private VueloDTO vuelo;
}
