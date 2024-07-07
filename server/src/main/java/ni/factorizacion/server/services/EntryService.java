package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.Entry;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EntryService {
    Optional<Entry> createEntry(User user, Terminal terminal, String description, Residence residence);

    Page<Entry> getEntriesByUser(User user, Pageable pageable);

    Page<Entry> getEntriesByResidence(Residence residence, Pageable pageable);

    Page<Entry> findAll(Pageable pageable);
}
