package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.input.SaveUserDto;
import ni.factorizacion.server.domain.dtos.input.TerminalCreateEntryDto;
import ni.factorizacion.server.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findByIdentifier(String identifier);

    Optional<User> createFrom(SaveUserDto dto);

    User save(User user);

    void update(User user, SaveUserDto dto);
}
