package ni.factorizacion.server.domain.dtos;

import lombok.Data;
import ni.factorizacion.server.domain.entities.TerminalType;

@Data
public class TerminalValidateQRDto {
    private TerminalType terminalType;
    private String password;
    private String tokenContent;
}
