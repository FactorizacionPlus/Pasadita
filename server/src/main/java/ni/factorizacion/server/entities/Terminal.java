package ni.factorizacion.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Terminal {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    @NotNull
    private TerminalType type;
    @NotNull
    private String password;

    @OneToMany
    @JsonIgnore
    private Set<Entry> entries;
}
