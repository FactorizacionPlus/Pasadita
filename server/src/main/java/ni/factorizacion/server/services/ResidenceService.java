package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.input.AssignResidentDto;
import ni.factorizacion.server.domain.dtos.input.SaveResidenceDto;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;
import ni.factorizacion.server.types.ControlException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResidenceService {

    List<Residence> findAll();

    Optional<Residence> findByDescription(String description);

    Optional<Residence> findById(UUID uuid);

    void saveResidence(SaveResidenceDto dto) throws ControlException;

    void updateResidence(String uuid, SaveResidenceDto dto) throws ControlException;

    void removeResidence(String uuid) throws ControlException;

    void assignResidenteToResidencia(Resident resident, Residence residence) throws ControlException;
}
