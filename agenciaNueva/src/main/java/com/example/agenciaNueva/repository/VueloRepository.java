package com.example.agenciaNueva.repository;

import com.example.agenciaNueva.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {
    @Query("SELECT v FROM Vuelo v WHERE v.destino = :destination " +
            "AND v.fechaSalida >= :dateFrom AND v.fechaVuelta <= :dateTo " +
            "AND v.asientos > 0")
    List<Vuelo> findAvailableFlights(@Param("destination") String destination,
                                     @Param("dateFrom") LocalDate dateFrom,
                                     @Param("dateTo") LocalDate dateTo);
}
