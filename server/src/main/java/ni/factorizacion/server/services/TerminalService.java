package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.TerminalType;

import java.util.Optional;

public interface TerminalService {
    Optional<Terminal> findTerminalByType(TerminalType terminalType, String password);
}
