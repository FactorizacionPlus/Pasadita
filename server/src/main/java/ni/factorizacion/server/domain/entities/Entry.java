package ni.factorizacion.server.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Entry {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    private LocalDateTime accessDate;
    @NotNull
    private String description;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Terminal terminal;
}
