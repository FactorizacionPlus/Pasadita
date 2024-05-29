package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.InvitedUser;
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
    public Optional<InvitedUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public InvitedUser createUser(GoogleUserInfo userInfo) throws ControlException {
        InvitedUser user = new InvitedUser();

        user.setFirstName(userInfo.getGivenName());
        user.setLastName(userInfo.getFamilyName());
        user.setEmail(userInfo.getEmail());
        user.setImageUrl(userInfo.getPicture());
        user.setStatus(Status.ACTIVE);

        repository.save(user);
        return user;
    }
}
