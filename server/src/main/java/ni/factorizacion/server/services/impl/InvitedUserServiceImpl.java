package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Status;
import ni.factorizacion.server.repositories.InvitedUserRepository;
import ni.factorizacion.server.services.InvitedUserService;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GoogleUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvitedUserServiceImpl implements InvitedUserService {

    @Autowired
    InvitedUserRepository repository;

    @Override
    public Optional<InvitedUser> findByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier);
    }

    @Override
    public Optional<InvitedUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public InvitedUser createUser(GoogleUserInfo userInfo) {
        InvitedUser user = new InvitedUser();

        user.setFirstName(userInfo.getGivenName());
        user.setLastName(userInfo.getFamilyName());
        user.setEmail(userInfo.getEmail());
        user.setImageUrl(userInfo.getPicture());
        user.setStatus(Status.ACTIVE);

        repository.save(user);
        return user;
    }

    @Override
    public Optional<InvitedUser> createFrom(RegisteredUser user) {
        if (user.getClass().equals(InvitedUser.class)) {
            return Optional.empty();
        }
        InvitedUser invited = new InvitedUser();

        invited.setUuid(user.getUuid());
        invited.setFirstName(user.getFirstName());
        invited.setLastName(user.getLastName());

        invited.setIdentifier(user.getIdentifier());
        invited.setIdentifierType(user.getIdentifierType());

        invited.setEmail(user.getEmail());
        invited.setImageUrl(user.getImageUrl());

        invited.setStatus(Status.ACTIVE);

        return Optional.of(invited);
    }

    @Override
    public void save(InvitedUser invitedUser) {
        repository.save(invitedUser);
    }
}
