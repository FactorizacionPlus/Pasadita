package ni.factorizacion.server.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ni.factorizacion.server.entities.IdentifierType;
import ni.factorizacion.server.entities.Residence;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveResidentDto extends SaveUserDto{
    @Email
    @NotEmpty
    private String email;
    private String imageUrl;

    @NotNull
    private Integer role;
}
