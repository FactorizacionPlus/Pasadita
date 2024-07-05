package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ni.factorizacion.server.domain.entities.TerminalType;

@Data
public class TerminalLoginDto {
    @NotNull
    private TerminalType terminalType;
    @NotEmpty
    private String password;
}
