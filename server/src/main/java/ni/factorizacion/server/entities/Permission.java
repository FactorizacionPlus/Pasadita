package ni.factorizacion.server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Permission {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    private Date creationDate;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    private Boolean authorized;

    @ManyToOne
    private InvitedUser invitedUser;
    @ManyToOne
    private Resident resident;
    @ManyToOne
    private Residence residence;
}
