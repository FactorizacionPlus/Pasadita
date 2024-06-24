package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.Entry;
import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.User;

import java.util.Optional;

public interface EntryService {
    Optional<Entry> createEntry(User user, Terminal terminal, String description);
}
