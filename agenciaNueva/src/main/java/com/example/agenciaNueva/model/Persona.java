package com.example.agenciaNueva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Personas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "persona",  fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ReservaVuelo> reservaVuelos = new ArrayList<>();

    @OneToMany(mappedBy = "persona",  fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ReservaHotel> reservaHoteles = new ArrayList<>();
}
