package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResidentRepository extends JpaRepository<Resident, UUID> {
    Optional<Resident> findByIdentifier(String identifier);

    List<Resident> findAllByResidence(Residence residence);
}
