package ni.factorizacion.server.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String identifier;
    @Enumerated(EnumType.STRING)
    private IdentifierType identifierType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    // Eager va a ser el ManyToOne
    // El OneToMany permite la comunicación de uno a muchos, no hace la unión y por tanto se recomienda que sea Lazy
    // Usa @JsonIgnore para omitir valores OneToMany y evitar circular dependencies
}
