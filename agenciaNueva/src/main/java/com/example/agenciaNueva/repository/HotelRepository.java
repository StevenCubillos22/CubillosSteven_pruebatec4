package com.example.agenciaNueva.repository;

import com.example.agenciaNueva.model.Hotel;
import com.example.agenciaNueva.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE h.lugar = :destination " +
            "AND h.fechaDesde <= :dateFrom AND h.fechaHasta >= :dateTo " +
            "AND h.estaReservado = false")
    List<Hotel> findAvailableHotels(@Param("destination") String destination,
                                    @Param("dateFrom") LocalDate dateFrom,
                                    @Param("dateTo") LocalDate dateTo);

}
