package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.repositories.RegisteredUserRepository;
import ni.factorizacion.server.services.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

    @Autowired
    RegisteredUserRepository repository;

    @Override
    public Optional<RegisteredUser> findByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier);
    }

    @Override
    public Optional<RegisteredUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void delete(RegisteredUser registeredUser) {
        repository.delete(registeredUser);
    }

    @Override
    public long getCount() {
        return repository.count();
    }
}
