package ni.factorizacion.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Residence {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private Integer maxHabitants;
    private String description;
    private Integer status;

    @OneToMany
    @JsonIgnore
    private Set<Resident> residents;
}
