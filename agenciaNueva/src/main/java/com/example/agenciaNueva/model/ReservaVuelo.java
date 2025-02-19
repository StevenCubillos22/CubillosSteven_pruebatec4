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
@Table(name = "reservas_vuelos")
public class ReservaVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private LocalDate fechaReserva;
    private String origen;
    private String destino;
    private Integer cantidadPersonas;
    private String tipoAsiento;


    //Se debe colocar EAGER para que funcione el getTurnos(sin usar Dto)
    //Si se coloca CascadeType.ALL no funciona el crear
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

}
