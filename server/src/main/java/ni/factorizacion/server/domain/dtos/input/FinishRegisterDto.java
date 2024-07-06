package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import ni.factorizacion.server.domain.entities.IdentifierType;

@Data
@NoArgsConstructor
public class FinishRegisterDto {

    @NotNull
    String email;

    @NotNull
    String identifier;

    @NotNull
    IdentifierType IdentifierType;
}