package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TerminalCreateEntryDto extends TerminalLoginDto {
    // Entry related stuff
    @NotEmpty
    private String description;

    // User related stuff
    @NotEmpty
    private String identifier;
}
