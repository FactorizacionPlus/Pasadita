package ni.factorizacion.server.services;

import ni.factorizacion.server.dtos.SaveUserDto;
import ni.factorizacion.server.dtos.UserSimpleDto;
import ni.factorizacion.server.entities.User;
import ni.factorizacion.server.types.ControlException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserSimpleDto> getAll();
    Optional<User> findByIdentifier(String identifier);
    void saveUser(SaveUserDto saveUserDto) throws ControlException;
    void updateUser(SaveUserDto saveUserDto) throws ControlException;
    void removeUser(String identifier) throws ControlException;
}
