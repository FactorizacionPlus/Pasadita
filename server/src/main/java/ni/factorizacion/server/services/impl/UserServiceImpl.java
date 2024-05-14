package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.dtos.SaveUserDto;
import ni.factorizacion.server.dtos.UserSimpleDto;
import ni.factorizacion.server.entities.User;
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
    public List<UserSimpleDto> getAll() {
        return repository.findAll().stream().map(UserSimpleDto::from).toList();
    }

    @Override
    public Optional<User> findByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier);
    }

    @Override
    public void saveUser(SaveUserDto saveUserDto) throws Exception {
        Optional<User> found = repository.findByIdentifier(saveUserDto.getIdentifier());
        if (found.isPresent()) {
            throw new Exception("User already exists");
        }
        User user = new User();

        user.setIdentifier(saveUserDto.getIdentifier());
        user.setFirstName(saveUserDto.getFirstName());
        user.setLastName(saveUserDto.getLastName());
        user.setPassword(saveUserDto.getPassword());

        repository.save(user);
    }

    @Override
    public void updateUser(SaveUserDto saveUserDto) throws Exception {
        Optional<User> found = repository.findByIdentifier(saveUserDto.getIdentifier());
        if (found.isEmpty()) {
            throw new Exception("User not found");
        }
        User user = found.get();

        user.setIdentifier(saveUserDto.getIdentifier());
        user.setFirstName(saveUserDto.getFirstName());
        user.setLastName(saveUserDto.getLastName());
        user.setPassword(saveUserDto.getPassword());

        repository.save(user);
    }

    @Override
    public void removeUser(String identifier) throws Exception {
        Optional<User> found = repository.findByIdentifier(identifier);
        if (found.isEmpty()) {
            throw new Exception("User not found");
        }

        repository.delete(found.get());
    }
}
