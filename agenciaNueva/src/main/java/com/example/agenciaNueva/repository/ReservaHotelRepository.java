package com.example.agenciaNueva.repository;

import com.example.agenciaNueva.model.ReservaHotel;
import com.example.agenciaNueva.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaHotelRepository extends JpaRepository<ReservaHotel, Integer> {
}
