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
public class ReservaHotelDTO {
    
    private Integer id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer noches;
    private String ciudad;
    private Integer cantPersonas;
    private String tipoHabitacion;
    private PersonaDTO persona;
    private HotelDTO hotel;
}
