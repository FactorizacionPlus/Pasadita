package ni.factorizacion.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Residence {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    private Integer maxHabitants;
    @NotNull
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    @JsonIgnore
    private Set<Resident> residents;
}
