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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vuelos")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String aerolinea;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalDate fechaVuelta;
    private int asientos;
    private Double precioPorPersona;

    @OneToMany(mappedBy = "vuelo",  fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ReservaVuelo> reservas= new ArrayList<>();
}
