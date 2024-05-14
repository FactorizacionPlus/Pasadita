package ni.factorizacion.server.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class InvitedUser extends User {
    private String email;
    @NotNull
    private String imageUrl;
}
