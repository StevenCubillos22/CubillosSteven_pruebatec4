package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.HotelDTO;
import com.example.agenciaNueva.dto.VueloDTO;

import java.time.LocalDate;
import java.util.List;

public interface IHotelService {

    String guardar(HotelDTO p);
    List<HotelDTO> buscarTodos();
    String eliminar(Integer id);
    String actualizar(Integer id, HotelDTO p);
    HotelDTO buscar(Integer id);
    List<HotelDTO> buscarDisponibles(String destination, LocalDate dateFrom, LocalDate dateTo);

}
