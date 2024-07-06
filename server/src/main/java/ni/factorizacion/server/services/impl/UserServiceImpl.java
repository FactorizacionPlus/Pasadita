package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.dtos.input.SaveUserDto;
import ni.factorizacion.server.domain.entities.User;
import ni.factorizacion.server.repositories.UserRepository;
import ni.factorizacion.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAllAnonymous();
    }

    @Override
    public List<User> findAllNoRole() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier);
    }

    @Override
    public Optional<User> createFrom(SaveUserDto dto) {
        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        user.setIdentifier(dto.getIdentifier());
        user.setIdentifierType(dto.getIdentifierType());

        return Optional.of(user);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void update(User user, SaveUserDto saveUserDto) {
        user.setIdentifier(saveUserDto.getIdentifier());
        user.setFirstName(saveUserDto.getFirstName());
        user.setLastName(saveUserDto.getLastName());
    }
}
