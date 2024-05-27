package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.repositories.InvitedUserRepository;
import ni.factorizacion.server.services.InvitedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvitedUserServiceImpl implements InvitedUserService {

    @Autowired
    InvitedUserRepository invitedUserRepository;

    @Override
    public Optional<InvitedUser> findByEmail(String email) {
        return invitedUserRepository.findByEmail(email);
    }
}
