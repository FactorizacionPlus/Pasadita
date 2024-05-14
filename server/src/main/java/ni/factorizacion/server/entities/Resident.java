package ni.factorizacion.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Resident extends InvitedUser {
    private Integer role;

    @ManyToOne
    private Residence residence;
}
