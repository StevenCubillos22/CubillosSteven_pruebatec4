package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.VueloDTO;
import com.example.agenciaNueva.model.Vuelo;

import java.time.LocalDate;
import java.util.List;

public interface IVueloService {
    String guardar(VueloDTO p);
    List<VueloDTO> buscarTodos();
    String eliminar(Integer id);
    String actualizar(Integer id, VueloDTO p);
    VueloDTO buscar(Integer id);

    List<VueloDTO> buscarDisponibles(String destination, LocalDate dateFrom, LocalDate dateTo);
}
