package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.ReservaVueloDTO;

import java.util.List;

public interface IReservaVueloService {
    String guardar(ReservaVueloDTO p);
    List<ReservaVueloDTO> buscarTodos();
    String eliminar(Integer id);

}
