package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.input.SaveUserDto;
import ni.factorizacion.server.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> findAllAnonymous(Pageable pageable);

    Page<User> findAll(Pageable pageable);

    long getCountAnonymous();

    long getCount();

    Optional<User> findByIdentifier(String identifier);

    Optional<User> createFrom(SaveUserDto dto);

    User save(User user);

    void update(User user, SaveUserDto dto);
}
