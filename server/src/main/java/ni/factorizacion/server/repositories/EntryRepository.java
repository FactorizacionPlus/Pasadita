package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntryRepository extends JpaRepository<Entry, UUID> {
}
