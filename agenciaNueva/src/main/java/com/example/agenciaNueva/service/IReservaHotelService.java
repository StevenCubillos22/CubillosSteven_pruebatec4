package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.ReservaHotelDTO;
import com.example.agenciaNueva.dto.ReservaVueloDTO;

import java.util.List;

public interface IReservaHotelService {
    String guardar(ReservaHotelDTO p);
    List<ReservaHotelDTO> buscarTodos();
    String eliminar(Integer id);
}
