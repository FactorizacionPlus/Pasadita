package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.input.SaveResidentDto;
import ni.factorizacion.server.domain.entities.Resident;
import ni.factorizacion.server.types.ControlException;

import java.util.List;
import java.util.Optional;

public interface ResidentService {
    List<Resident> findAll();

    Optional<Resident> findByIdentifier(String identifier);

    void saveUser(SaveResidentDto dto) throws ControlException;

    void updateUser(SaveResidentDto dto) throws ControlException;

    void removeUser(String identifier) throws ControlException;
}
