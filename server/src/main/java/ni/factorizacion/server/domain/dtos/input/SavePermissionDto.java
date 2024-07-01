package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SavePermissionDto {
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;

    private String invitedUser;
}
