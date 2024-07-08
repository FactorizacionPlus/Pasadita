package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.TerminalType;

import java.util.List;
import java.util.Optional;

public interface TerminalService {
    Optional<Terminal> findTerminal(TerminalType terminalType);

    Optional<Terminal> findTerminalByType(TerminalType terminalType, String password);

    List<Terminal> findAll();

    long getCount();
}
