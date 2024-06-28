package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AssignResidentDto {
    @NotEmpty
    private String identifier;
    @NotEmpty
    private String uuid;

}
