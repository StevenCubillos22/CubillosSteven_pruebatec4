package com.example.agenciaNueva.repository;

import com.example.agenciaNueva.model.ReservaVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaVueloRepository extends JpaRepository<ReservaVuelo, Integer> {


}
