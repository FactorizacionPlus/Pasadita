package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TerminalCreateEntryDto {
    // Entry related stuff
    @NotEmpty
    private String description;

    // User related stuff
    @NotEmpty
    private String identifier;
}
