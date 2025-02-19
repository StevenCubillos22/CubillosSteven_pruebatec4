package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.HotelDTO;
import com.example.agenciaNueva.dto.VueloDTO;
import com.example.agenciaNueva.model.Hotel;
import com.example.agenciaNueva.model.Vuelo;
import com.example.agenciaNueva.repository.HotelRepository;
import com.example.agenciaNueva.repository.VueloRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HotelService implements IHotelService{

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public String guardar(HotelDTO p) {
        hotelRepository.save(mapDtoToEntity(p));
        return "Se creó con éxito";
    }

    @Override
    public List<HotelDTO> buscarTodos() {
        return hotelRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String eliminar(Integer id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if (Objects.nonNull(hotel)) {
            if (!hotel.getReservasHoteles().isEmpty())
                return "No puede eliminar, tiene reservas de hoteles registrados.";
            hotelRepository.deleteById(id);
            return "Se eliminó con éxito.";
        } else {
            return "No se encontró el elemento con ID " + id + ". No se pudo eliminar.";
        }
    }

    @Override
    public String actualizar(Integer id, HotelDTO hotelDTO) {
        if (hotelDTO.getId() == null) {
            return "El campo ID no puede ser nulo. No se pudo actualizar.";
        }
        Hotel hotel = hotelRepository.findById(hotelDTO.getId()).orElse(null);
        if (Objects.nonNull(hotel)) {
            hotelRepository.save(mapDtoToEntity(hotelDTO));
            return "Se actualizó con éxito.";
        } else {
            return "No se encontró el elemento con ID " + hotelDTO.getId() + ". No se pudo actualizar.";
        }
    }

    @Override
    public HotelDTO buscar(Integer id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        return Objects.nonNull(hotel) ? mapEntityToDto(hotel) : null;
    }

    @Override
    public List<HotelDTO> buscarDisponibles(String destination, LocalDate dateFrom, LocalDate dateTo) {
        return hotelRepository.findAvailableHotels(destination, dateFrom, dateTo)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public Hotel mapDtoToEntity(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setNombre(hotelDTO.getNombre());
        hotel.setLugar(hotelDTO.getLugar());
        hotel.setTipoHabitacion(hotelDTO.getTipoHabitacion());
        hotel.setPrecioHabitacion(hotelDTO.getPrecioHabitacion());
        hotel.setFechaDesde(hotelDTO.getFechaDesde());
        hotel.setFechaHasta(hotelDTO.getFechaHasta());
        return hotel;
    }

    public HotelDTO mapEntityToDto(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setNombre(hotel.getNombre());
        hotelDTO.setLugar(hotel.getLugar());
        hotelDTO.setTipoHabitacion(hotel.getTipoHabitacion());
        hotelDTO.setPrecioHabitacion(hotel.getPrecioHabitacion());
        hotelDTO.setFechaDesde(hotel.getFechaDesde());
        hotelDTO.setFechaHasta(hotel.getFechaHasta());
        return hotelDTO;
    }
}
