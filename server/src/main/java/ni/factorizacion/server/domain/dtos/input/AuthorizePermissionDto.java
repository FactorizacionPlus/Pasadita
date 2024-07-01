package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AuthorizePermissionDto {
    @NotNull
    private UUID permission;
    @NotNull
    private boolean authorized;
}
