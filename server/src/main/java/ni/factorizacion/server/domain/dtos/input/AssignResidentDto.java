package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;

@Data
public class AssignResidentDto {
    @NotEmpty
    private String identifier;
    @NotEmpty
    private UUID uuid;

}
