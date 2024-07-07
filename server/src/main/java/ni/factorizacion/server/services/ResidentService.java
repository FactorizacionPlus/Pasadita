package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.input.SaveResidentDto;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;
import ni.factorizacion.server.domain.entities.ResidentRole;
import ni.factorizacion.server.types.ControlException;

import java.util.List;
import java.util.Optional;

public interface ResidentService {
    List<Resident> findAll();

    List<Resident> findAllByResidence(Residence residence);

    Optional<Resident> findByIdentifier(String identifier);

    long getCount();

    void saveUser(SaveResidentDto dto) throws ControlException;

    void updateUser(SaveResidentDto dto) throws ControlException;

    void removeUser(String identifier) throws ControlException;

    Optional<Resident> createFrom(RegisteredUser user);

    void changeResidentRole(Resident resident, ResidentRole role);

    void save(Resident resident);
}
