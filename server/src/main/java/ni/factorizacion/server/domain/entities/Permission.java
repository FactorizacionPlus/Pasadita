package ni.factorizacion.server.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Permission {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    @CreationTimestamp
    private LocalDateTime creationDate;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    private Boolean authorized;

    @ManyToOne
    @JsonIgnore
    private InvitedUser invitedUser;
    @ManyToOne
    @JsonIgnore
    private Resident resident;
    @ManyToOne
    @JsonIgnore
    private Residence residence;
}
