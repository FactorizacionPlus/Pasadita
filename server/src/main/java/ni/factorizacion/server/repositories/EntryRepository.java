package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.Entry;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EntryRepository extends JpaRepository<Entry, UUID> {
    List<Entry> getEntriesByUser(User user);
    List<Entry> getEntriesByResidence(Residence residence);
}
