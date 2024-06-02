package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.TerminalType;
import ni.factorizacion.server.repositories.TerminalRepository;
import ni.factorizacion.server.services.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TerminalServiceImpl implements TerminalService {
    @Autowired
    TerminalRepository terminalRepository;

    @Override
    public Optional<Terminal> findTerminalByType(TerminalType terminalType, String password) {
        return terminalRepository.findByTypeAndPassword(terminalType, password);
    }
}
