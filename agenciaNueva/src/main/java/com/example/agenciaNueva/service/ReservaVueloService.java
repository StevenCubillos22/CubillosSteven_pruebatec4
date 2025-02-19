package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.PersonaDTO;
import com.example.agenciaNueva.dto.ReservaVueloDTO;
import com.example.agenciaNueva.dto.VueloDTO;
import com.example.agenciaNueva.model.Persona;
import com.example.agenciaNueva.model.ReservaVuelo;
import com.example.agenciaNueva.model.Vuelo;
import com.example.agenciaNueva.repository.ReservaVueloRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReservaVueloService implements IReservaVueloService{

    private final ReservaVueloRepository reservaVueloRepository;
    private final IPersonaService personaService;
    private final VueloService vueloService;

    public ReservaVueloService(ReservaVueloRepository reservaVueloRepository, IPersonaService personaService, VueloService vueloService) {
        this.reservaVueloRepository = reservaVueloRepository;
        this.personaService = personaService;
        this.vueloService = vueloService;
    }

    @Override
    public String guardar(ReservaVueloDTO p) {
        if (p.getPersona() == null || p.getPersona().getId() == null || p.getVuelo().getId() == null){
            return "El ID de usuario u vuelo no puede ser nulo. No se pudo crear :(.";
        }

        if (personaService.buscar(p.getPersona().getId()) == null || vueloService.buscar(p.getVuelo().getId()) == null)
            return "El usuario o el vuelo no existen.";

        Vuelo vuelo = vueloService.mapDtoToEntity(vueloService.buscar(p.getVuelo().getId()));
        reservaVueloRepository.save(mapEntityToDto(p));
        return "Se creó con éxito. :D, el monto total es de: "+vuelo.getPrecioPorPersona() * p.getCantidadPersonas();
    }

    @Override
    public List<ReservaVueloDTO> buscarTodos() {
        return reservaVueloRepository.findAll().stream()
                //.map(this::mapEntityToDto)
                .map(p -> mapDtoToEntity(p))
                .toList();
    }

    @Override
    public String eliminar(Integer id) {
        ReservaVuelo reservaVuelo = reservaVueloRepository.findById(id).orElse(null);
        if (Objects.nonNull(reservaVuelo)) {
            reservaVueloRepository.deleteById(id);
            return "Se eliminó con éxito.";
        } else {
            return "No se encontró el elemento con el ID " + id + ". No se pudo eliminar.";
        }

    }

    public ReservaVuelo mapEntityToDto (ReservaVueloDTO reservaVueloDTO){
        ReservaVuelo reserva = new ReservaVuelo();
        reserva.setId(reservaVueloDTO.getId());
        reserva.setFechaReserva(reservaVueloDTO.getFechaReserva());
        reserva.setOrigen(reservaVueloDTO.getOrigen());
        reserva.setDestino(reservaVueloDTO.getDestino());
        reserva.setCantidadPersonas(reservaVueloDTO.getCantidadPersonas());
        reserva.setTipoAsiento(reservaVueloDTO.getTipoAsiento());
        //reserva.setMontoTotal(reservaVueloDTO.getMontoTotal());

        Persona persona = new Persona();
        persona.setId(reservaVueloDTO.getPersona().getId());
        reserva.setPersona(persona);

        Vuelo vuelo = new Vuelo();
        vuelo.setId(reservaVueloDTO.getVuelo().getId());
        reserva.setVuelo(vuelo);

        return reserva;
    }

    public ReservaVueloDTO mapDtoToEntity (ReservaVuelo reservaVuelo){
        ReservaVueloDTO reservaVueloDTO = new ReservaVueloDTO();
        reservaVueloDTO.setId(reservaVuelo.getId());
        reservaVueloDTO.setFechaReserva(reservaVuelo.getFechaReserva());
        reservaVueloDTO.setOrigen(reservaVuelo.getOrigen());
        reservaVueloDTO.setDestino(reservaVuelo.getDestino());
        reservaVueloDTO.setCantidadPersonas(reservaVuelo.getCantidadPersonas());
        reservaVueloDTO.setTipoAsiento(reservaVuelo.getTipoAsiento());
        //reservaVueloDTO.setMontoTotal(reservaVuelo.getMontoTotal());


        if (reservaVuelo.getPersona() != null) {
            reservaVueloDTO.setPersona(new PersonaDTO(reservaVuelo.getPersona().getId(), reservaVuelo.getPersona().getNombre(), reservaVuelo.getPersona().getApellido()));
        }

        if (reservaVuelo.getVuelo() != null) {
            reservaVueloDTO.setVuelo(new VueloDTO(reservaVuelo.getVuelo().getId(), reservaVuelo.getVuelo().getAerolinea(), reservaVuelo.getVuelo().getOrigen(), reservaVuelo.getVuelo().getDestino(), reservaVuelo.getVuelo().getFechaSalida(), reservaVuelo.getVuelo().getFechaVuelta(),reservaVuelo.getVuelo().getAsientos(), reservaVuelo.getVuelo().getPrecioPorPersona()));
        }
        return reservaVueloDTO;
    }

}
