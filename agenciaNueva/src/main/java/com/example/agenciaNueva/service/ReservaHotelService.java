package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.*;
import com.example.agenciaNueva.model.*;
import com.example.agenciaNueva.repository.ReservaHotelRepository;
import com.example.agenciaNueva.repository.ReservaVueloRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ReservaHotelService implements IReservaHotelService{


    private final ReservaHotelRepository reservaHotelRepository;
    private final IPersonaService personaService;
    private final HotelService hotelService;

    public ReservaHotelService(ReservaHotelRepository reservaHotelRepository, IPersonaService personaService, HotelService hotelService) {
        this.reservaHotelRepository = reservaHotelRepository;
        this.personaService = personaService;
        this.hotelService = hotelService;
    }

    @Override
    public String guardar(ReservaHotelDTO p) {
        if (p.getPersona() == null || p.getPersona().getId() == null || p.getHotel().getId() == null){
            return "El ID de usuario u hotel no puede ser nulo. No se pudo crear :(.";
        }

        if (personaService.buscar(p.getPersona().getId()) == null || hotelService.buscar(p.getHotel().getId()) == null)
            return "El usuario o el hotel no existen.";

        Hotel hotel = hotelService.mapDtoToEntity(hotelService.buscar(p.getHotel().getId()));
        reservaHotelRepository.save(mapEntityToDto(p));
        return "Se creó con éxito. :D, el monto total es de: "+hotel.getPrecioHabitacion() * p.getCantPersonas();

//        reservaHotelRepository.save(mapEntityToDto(p));
//        return "Se creó con éxito. :D";
    }

    @Override
    public List<ReservaHotelDTO> buscarTodos() {
        return reservaHotelRepository.findAll().stream()
                //.map(this::mapEntityToDto)
                .map(p -> mapDtoToEntity(p))
                .toList();
    }

    @Override
    public String eliminar(Integer id) {
        ReservaHotel reservaHotel = reservaHotelRepository.findById(id).orElse(null);
        if (Objects.nonNull(reservaHotel)) {
            reservaHotelRepository.deleteById(id);
            return "Se ha eliminado la reserva con éxito.";
        } else {
            return "No se encontró el elemento con el ID " + id + ". No se pudo eliminar.";
        }

    }

    public ReservaHotel mapEntityToDto (ReservaHotelDTO reservaHotelDTO){
        ReservaHotel reservaHotel = new ReservaHotel();
        reservaHotel.setId(reservaHotelDTO.getId());
        reservaHotel.setCheckIn(reservaHotelDTO.getCheckIn());
        reservaHotel.setCheckOut(reservaHotelDTO.getCheckOut());
        reservaHotel.setNoches(reservaHotelDTO.getNoches());
        reservaHotel.setCiudad(reservaHotelDTO.getCiudad());
        reservaHotel.setCantPersonas(reservaHotelDTO.getCantPersonas());
        reservaHotel.setTipoHabitacion(reservaHotelDTO.getTipoHabitacion());

        Persona persona = new Persona();
        persona.setId(reservaHotelDTO.getPersona().getId());
        reservaHotel.setPersona(persona);

        Hotel hotel = new Hotel();
        hotel.setId(reservaHotelDTO.getHotel().getId());
        reservaHotel.setHotel(hotel);

        return reservaHotel;
    }

    public ReservaHotelDTO mapDtoToEntity (ReservaHotel reservaHotel){
        ReservaHotelDTO reservaHotelDTO = new ReservaHotelDTO();
        reservaHotelDTO.setId(reservaHotel.getId());
        reservaHotelDTO.setCheckIn(reservaHotel.getCheckIn());
        reservaHotelDTO.setCheckOut(reservaHotel.getCheckOut());
        reservaHotelDTO.setNoches(reservaHotel.getNoches());
        reservaHotelDTO.setCiudad(reservaHotel.getCiudad());
        reservaHotelDTO.setCantPersonas(reservaHotel.getCantPersonas());
        reservaHotelDTO.setTipoHabitacion(reservaHotel.getTipoHabitacion());

        if (reservaHotel.getPersona() != null) {
            reservaHotelDTO.setPersona(new PersonaDTO(
                    reservaHotel.getPersona().getId(),
                    reservaHotel.getPersona().getNombre(),
                    reservaHotel.getPersona().getApellido()
            ));
        }

        if (reservaHotel.getHotel() != null) {
            reservaHotelDTO.setHotel(new HotelDTO(
                    reservaHotel.getHotel().getId(),
                    reservaHotel.getHotel().getNombre(),
                    reservaHotel.getHotel().getLugar(),
                    reservaHotel.getHotel().getTipoHabitacion(),
                    reservaHotel.getHotel().getPrecioHabitacion(),
                    reservaHotel.getHotel().getFechaDesde(),
                    reservaHotel.getHotel().getFechaHasta(),
                    reservaHotel.getHotel().isEstaReservado()
            ));
        }
        return reservaHotelDTO;
    }
}
