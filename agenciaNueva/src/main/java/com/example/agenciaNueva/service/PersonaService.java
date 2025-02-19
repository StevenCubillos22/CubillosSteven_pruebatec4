package com.example.agenciaNueva.service;

import com.example.agenciaNueva.dto.PersonaDTO;
import com.example.agenciaNueva.model.Persona;
import com.example.agenciaNueva.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonaService implements IPersonaService{

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }


    @Override
    public String guardar(PersonaDTO p) {
        personaRepository.save(mapDtoToEntity(p));
        return "Se creó con éxito";
    }

    @Override
    public List<PersonaDTO> buscarTodos() {
        return personaRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    @Override
    public String eliminar(Integer id) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (Objects.nonNull(persona)) {
            if (!persona.getReservaVuelos().isEmpty())
                return "No puede eliminar, tiene turnos registrados.";
            personaRepository.deleteById(id);
            return "Se eliminó con éxito.";
        } else {
            return "No se encontró el elemento con ID " + id + ". No se pudo eliminar.";
        }
    }

    @Override
    public String actualizarForma1(PersonaDTO p) {
        if (p.getId() == null){
            return "El campo ID no puede ser nulo. No se pudo actualizar.";
        }
        Persona persona = personaRepository.findById(p.getId()).orElse(null);
        if (Objects.nonNull(persona)) {
            personaRepository.save(mapDtoToEntity(p));
            return "Se actualizó con éxito.";
        } else {
            return "No se encontró el elemento con ID " + p.getId() + ". No se pudo actualizar.";
        }
    }

//    public String actualizarForma2(PersonaDTO p) {
//        if (p.getId() == null){
//            throw new RuntimeException("El campo ID no puede ser nulo. No se pudo actualizar.");
//        }
//        Persona persona = personaRepository.findById(p.getId()).orElse(null);
//        if (Objects.nonNull(persona)) {
//            personaRepository.save(mapDtoToEntity(p));
//            return "Se actualizó con éxito.";
//        } else {
//            throw new RuntimeException("No se encontró el elemento con ID " + p.getId() +". No se pudo actualizar.");
//        }
//    }


    public String actualizarForma2(Integer id, PersonaDTO personaDTO) {
        Persona persona = personaRepository.findById(id).orElse(null);

        if (persona == null) {
            throw new RuntimeException("Persona no encontrada con ID: " + id);
        }

        // Actualizar campos
        persona.setNombre(personaDTO.getNombre());
        persona.setApellido(personaDTO.getApellido());

        personaRepository.save(persona);
        return "Persona actualizada correctamente";
    }


    @Override
    public Persona buscar(Integer id) {
        return personaRepository.findById(id).orElse(null);
    }

    // Mapeo de Entidad a DTO
    public PersonaDTO mapEntityToDto(Persona persona) {
        return new PersonaDTO(
                persona.getId(),
                persona.getNombre(),
                persona.getApellido()
        );
    }

    // Mapeo de DTO a Entidad
    public Persona mapDtoToEntity(PersonaDTO personaDTO) {
        return new Persona(
                personaDTO.getId(),
                personaDTO.getNombre(),
                personaDTO.getApellido(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
