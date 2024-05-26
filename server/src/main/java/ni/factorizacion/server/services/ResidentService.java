package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.ResidentSimpleDto;
import ni.factorizacion.server.domain.dtos.SaveResidentDto;
import ni.factorizacion.server.types.ControlException;

import java.util.List;
import java.util.Optional;

public interface ResidentService {
    List<ResidentSimpleDto> getAll();

    Optional<ResidentSimpleDto> findByIdentifier(String identifier);

    void saveUser(SaveResidentDto dto) throws ControlException;

    void updateUser(SaveResidentDto dto) throws ControlException;

    void removeUser(String identifier) throws ControlException;
}