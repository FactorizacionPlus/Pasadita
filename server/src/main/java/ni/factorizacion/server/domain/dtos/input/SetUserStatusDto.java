package ni.factorizacion.server.domain.dtos.input;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ni.factorizacion.server.domain.entities.Status;

@Data
public class SetUserStatusDto {
    @NotEmpty
    private String identifier;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
