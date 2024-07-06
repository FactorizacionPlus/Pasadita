package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ni.factorizacion.server.domain.entities.ResidentRole;

@Data
@NoArgsConstructor
public class ResidentChangeRolDto {
    @NotNull
    String identifier;

    @NotNull
    ResidentRole residentRole;
}
