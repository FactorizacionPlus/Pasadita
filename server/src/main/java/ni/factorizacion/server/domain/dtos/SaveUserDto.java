package ni.factorizacion.server.domain.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ni.factorizacion.server.domain.entities.IdentifierType;

@Data
public class SaveUserDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @NotEmpty
    private String identifier;
    @NotNull
    @Enumerated(EnumType.STRING)
    private IdentifierType identifierType;
}
