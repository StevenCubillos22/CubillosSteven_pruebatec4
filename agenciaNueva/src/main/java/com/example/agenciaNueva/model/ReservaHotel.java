package com.example.agenciaNueva.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas_hoteles")
public class ReservaHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer noches;
    private String ciudad;
    private Integer cantPersonas;
    private String tipoHabitacion;

    //Se debe colocar EAGER para que funcione el getTurnos(sin usar Dto)
    //Si se coloca CascadeType.ALL no funciona el crear
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
