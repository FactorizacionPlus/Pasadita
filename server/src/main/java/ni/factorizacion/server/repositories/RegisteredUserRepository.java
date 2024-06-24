package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, UUID> {
    Optional<RegisteredUser> findByEmail(String email);

    Optional<RegisteredUser> findByIdentifier(String identifier);
}
