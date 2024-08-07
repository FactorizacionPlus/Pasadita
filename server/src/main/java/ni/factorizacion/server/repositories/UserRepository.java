package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByIdentifier(String identifier);

    @Query("SELECT u FROM User u WHERE u.class = User AND u.identifier = :identifier")
    Optional<User> findAnonymousByIdentifier(@Param("identifier") String identifier);

    @Query("from User where class = User")
    Page<User> findAllAnonymous(Pageable pageable);

    @Query("SELECT COUNT(*) FROM User WHERE class = User")
    long countAnonymous();
}
