package ni.factorizacion.server.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("Invited")
public class InvitedUser extends User {
    @NotNull
    private String email;
    private String imageUrl;
}