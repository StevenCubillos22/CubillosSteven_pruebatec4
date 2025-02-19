package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.HotelDTO;
import com.example.agenciaNueva.model.Hotel;
import com.example.agenciaNueva.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    private HotelDTO crearHotelDTO() {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(1);
        hotelDTO.setNombre("Hotel Ejemplo");
        hotelDTO.setLugar("Madrid");
        hotelDTO.setTipoHabitacion("Doble");
        hotelDTO.setPrecioHabitacion(100.0);
        hotelDTO.setFechaDesde(LocalDate.of(2025, 2, 18));
        hotelDTO.setFechaHasta(LocalDate.of(2025, 2, 25));
        hotelDTO.setEstaReservado(false);
        return hotelDTO;
    }

    private Hotel crearHotel() {
        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setNombre("Hotel Ejemplo");
        hotel.setLugar("Madrid");
        hotel.setTipoHabitacion("Doble");
        hotel.setPrecioHabitacion(100.0);
        hotel.setFechaDesde(LocalDate.of(2025, 2, 18));
        hotel.setFechaHasta(LocalDate.of(2025, 2, 25));
        hotel.setEstaReservado(false);
        return hotel;
    }

    @Test
    void guardarHotel() {
        HotelDTO hotelDTO = crearHotelDTO();
        String resultado = hotelService.guardar(hotelDTO);
        assertEquals("Se creó con éxito", resultado);
    }

    @Test
    void buscarTodosLosHoteles() {
        hotelRepository.save(crearHotel());
        List<HotelDTO> hoteles = hotelService.buscarTodos();
        assertFalse(hoteles.isEmpty());
    }

    @Test
    void eliminarHotelNoEncontrado() {
        String resultado = hotelService.eliminar(99);
        assertEquals("No se encontró el elemento con ID 99. No se pudo eliminar.", resultado);
    }

    @Test
    void buscarHotelPorId() {
        Hotel hotel = crearHotel();
        hotelRepository.save(hotel);
        HotelDTO resultado = hotelService.buscar(1);
        assertNotNull(resultado);
        assertEquals("Hotel Ejemplo", resultado.getNombre());
    }

    @Test
    void buscarHotelPorIdNoEncontrado() {
        HotelDTO resultado = hotelService.buscar(99);
        assertNull(resultado);
    }

    @Test
    void buscarHotelesDisponibles() {
        Hotel hotel = crearHotel();
        hotelRepository.save(hotel);
        List<HotelDTO> hoteles = hotelService.buscarDisponibles("Madrid", LocalDate.of(2025, 2, 18), LocalDate.of(2025, 2, 25));
        assertFalse(hoteles.isEmpty());
    }
}
