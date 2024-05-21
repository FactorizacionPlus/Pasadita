package ni.factorizacion.server.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Administrator {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    private String identifier;
    @NotNull
    @Enumerated(EnumType.STRING)
    private IdentifierType identifierType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private String email;
    private String imageUrl;
}
