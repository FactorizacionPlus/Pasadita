package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ni.factorizacion.server.domain.entities.ResidentRole;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveResidentDto extends SaveUserDto {
    @Email
    @NotEmpty
    private String email;
    private String imageUrl;

    @NotNull
    private ResidentRole role;
}
