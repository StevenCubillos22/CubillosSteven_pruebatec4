package com.example.agenciaNueva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hoteles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String nombre;
    private String lugar;
    private String tipoHabitacion;
    private Double precioHabitacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private boolean estaReservado;

    @OneToMany(mappedBy = "hotel",  fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ReservaHotel> reservasHoteles= new ArrayList<>();
}
