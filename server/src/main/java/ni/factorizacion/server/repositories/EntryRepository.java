package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.Entry;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntryRepository extends JpaRepository<Entry, UUID> {
    Page<Entry> getEntriesByUser(User user, Pageable pageable);

    Page<Entry> getEntriesByResidence(Residence residence, Pageable pageable);

    Page<Entry> getEntriesByTerminal(Terminal terminal, Pageable pageable);
}
