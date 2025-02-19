package com.example.agenciaNueva.repository;

import com.example.agenciaNueva.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
