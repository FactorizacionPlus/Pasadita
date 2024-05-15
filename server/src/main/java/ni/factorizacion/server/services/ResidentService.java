package ni.factorizacion.server.services;

import ni.factorizacion.server.dtos.ResidentSimpleDto;
import ni.factorizacion.server.dtos.SaveUserDto;
import ni.factorizacion.server.types.ControlException;

import java.util.List;
import java.util.Optional;

public interface ResidentService {
    List<ResidentSimpleDto> getAll();

    Optional<ResidentSimpleDto> findByIdentifier(String identifier);

    void saveUser(SaveUserDto saveUserDto) throws ControlException;

    void updateUser(SaveUserDto saveUserDto) throws ControlException;

    void removeUser(String identifier) throws ControlException;
}
