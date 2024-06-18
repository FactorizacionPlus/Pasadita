package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ResidenceRepository extends JpaRepository<Residence, UUID> {
    Optional<Residence> findByDescription(String description);
}
