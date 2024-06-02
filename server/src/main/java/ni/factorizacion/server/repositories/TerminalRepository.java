package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.TerminalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TerminalRepository extends JpaRepository<Terminal, UUID> {
    Optional<Terminal> findByTypeAndPassword(TerminalType type, String password);
}
