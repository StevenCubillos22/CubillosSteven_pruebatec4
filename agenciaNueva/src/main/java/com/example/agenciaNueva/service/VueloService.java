package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.VueloDTO;
import com.example.agenciaNueva.model.Vuelo;
import com.example.agenciaNueva.repository.VueloRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VueloService implements IVueloService{

    private final VueloRepository vueloRepository;

    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    @Override
    public String guardar(VueloDTO p) {
        vueloRepository.save(mapDtoToEntity(p));
        return "Se creó con éxito";
    }

    @Override
    public List<VueloDTO> buscarTodos() {
        return vueloRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String eliminar(Integer id) {
        Vuelo vuelo = vueloRepository.findById(id).orElse(null);
        if (Objects.nonNull(vuelo)) {
            if (!vuelo.getReservas().isEmpty())
                return "No puede eliminar, tiene turnos registrados.";
            vueloRepository.deleteById(id);
            return "Se eliminó con éxito.";
        } else {
            return "No se encontró el elemento con ID " + id + ". No se pudo eliminar.";
        }
    }

    @Override
    public String actualizar(Integer id, VueloDTO vueloDTO) {
        if (vueloDTO.getId() == null) {
            return "El campo ID no puede ser nulo. No se pudo actualizar.";
        }
        Vuelo vuelo = vueloRepository.findById(vueloDTO.getId()).orElse(null);
        if (Objects.nonNull(vuelo)) {
            vueloRepository.save(mapDtoToEntity(vueloDTO));
            return "Se actualizó con éxito.";
        } else {
            return "No se encontró el elemento con ID " + vueloDTO.getId() + ". No se pudo actualizar.";
        }
    }

    @Override
    public VueloDTO buscar(Integer id) {
        Vuelo vuelo = vueloRepository.findById(id).orElse(null);
        return Objects.nonNull(vuelo) ? mapEntityToDto(vuelo) : null;
    }

    @Override
    public List<VueloDTO> buscarDisponibles(String destination, LocalDate dateFrom, LocalDate dateTo) {
        return vueloRepository.findAvailableFlights(destination, dateFrom, dateTo)
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public Vuelo mapDtoToEntity(VueloDTO vueloDTO) {
        Vuelo vuelo = new Vuelo();
        vuelo.setId(vueloDTO.getId());
        vuelo.setAerolinea(vueloDTO.getAerolinea());
        vuelo.setOrigen(vueloDTO.getOrigen());
        vuelo.setDestino(vueloDTO.getDestino());
        vuelo.setFechaSalida(vueloDTO.getFechaSalida());
        vuelo.setFechaVuelta(vueloDTO.getFechaVuelta());
        vuelo.setAsientos(vueloDTO.getAsientos());
        vuelo.setPrecioPorPersona(vueloDTO.getPrecioPorPersona());
        return vuelo;
    }

    public VueloDTO mapEntityToDto(Vuelo vuelo) {
        VueloDTO vueloDTO = new VueloDTO();
        vueloDTO.setId(vuelo.getId());
        vueloDTO.setAerolinea(vuelo.getAerolinea());
        vueloDTO.setOrigen(vuelo.getOrigen());
        vueloDTO.setDestino(vuelo.getDestino());
        vueloDTO.setFechaSalida(vuelo.getFechaSalida());
        vueloDTO.setFechaVuelta(vuelo.getFechaVuelta());
        vueloDTO.setAsientos(vuelo.getAsientos());
        vueloDTO.setPrecioPorPersona(vuelo.getPrecioPorPersona());
        return vueloDTO;
    }
}
