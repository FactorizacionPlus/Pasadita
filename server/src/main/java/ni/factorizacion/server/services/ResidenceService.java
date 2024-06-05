package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.ResidenceSimpleDto;
import ni.factorizacion.server.domain.dtos.ResidentSimpleDto;
import ni.factorizacion.server.domain.dtos.SaveResidenceDto;
import ni.factorizacion.server.domain.dtos.SaveResidentDto;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.types.ControlException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResidenceService {

    List<ResidenceSimpleDto> getAll();
    Optional<Residence> findByDescription(String description);
    void saveResidence(SaveResidenceDto dto) throws ControlException;

    void updateResidence(String uuid, SaveResidenceDto dto) throws ControlException;
    void removeResidence(String uuid) throws ControlException;
}
