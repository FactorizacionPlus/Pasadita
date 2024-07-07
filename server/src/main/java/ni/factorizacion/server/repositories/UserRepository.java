package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByIdentifier(String identifier);

    @Query("from User where class = User")
    List<User> findAllAnonymous();

    @Query("SELECT COUNT(*) FROM User WHERE class = User")
    long countAnonymous();
}
