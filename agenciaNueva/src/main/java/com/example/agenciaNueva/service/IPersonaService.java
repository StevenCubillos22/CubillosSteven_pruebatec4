package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.PersonaDTO;
import com.example.agenciaNueva.model.Persona;

import java.util.List;

public interface IPersonaService {
    String guardar(PersonaDTO p);
    List<PersonaDTO> buscarTodos();
    String eliminar(Integer id);
    String actualizarForma1(PersonaDTO p);
    String actualizarForma2(Integer id, PersonaDTO p) throws Exception;
    Persona buscar(Integer id);

}
