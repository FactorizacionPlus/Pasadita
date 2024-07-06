package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    List<Token> findByUserAndActive(RegisteredUser user, Boolean active);

    Optional<Token> findByContent(String token);

    @Modifying
    @Query("UPDATE Token t SET t.active = false WHERE t.type = 'QR' and t.user = :user")
    void invalidateQrTokensByUser(@Param("user") RegisteredUser user);
}
