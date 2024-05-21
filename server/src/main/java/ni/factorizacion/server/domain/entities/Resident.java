package ni.factorizacion.server.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Resident extends InvitedUser {
    @NotNull
    private Integer role;

    @ManyToOne
    private Residence residence;
}
