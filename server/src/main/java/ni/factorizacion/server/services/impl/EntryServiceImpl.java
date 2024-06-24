package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.repositories.EntryRepository;
import ni.factorizacion.server.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {
    @Autowired
    EntryRepository entryRepository;

    @Override
    public Optional<Entry> createEntry(User user, Terminal terminal, String description) {
        Entry entry = new Entry();

        if (user instanceof Administrator) {
            return Optional.empty();
        }

        entry.setAccessDate(LocalDateTime.now());
        entry.setDescription("");
        entry.setUser(user);
        entry.setTerminal(terminal);

        entryRepository.save(entry);

        return Optional.of(entry);
    }
}
