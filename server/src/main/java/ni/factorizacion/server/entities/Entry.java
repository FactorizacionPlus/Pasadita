package ni.factorizacion.server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Entry {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    private Date accessDate;
    @NotNull
    private String description;

    @ManyToOne
    private User user;
    @ManyToOne
    private Terminal terminal;
}
