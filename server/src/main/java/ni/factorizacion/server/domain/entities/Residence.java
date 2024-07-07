package ni.factorizacion.server.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "residence")
    @JsonIgnore
    private List<Resident> residents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "residence")
    @JsonIgnore
    private List<Entry> entries;
}
