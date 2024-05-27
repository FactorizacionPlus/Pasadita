package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvitedUserRepository extends JpaRepository<Resident, UUID> {
    Optional<InvitedUser> findByEmail(String email);
}
