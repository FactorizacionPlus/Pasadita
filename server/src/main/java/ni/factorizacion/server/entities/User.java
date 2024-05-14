package ni.factorizacion.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identifier")
    private String identifier;
    @Column(name = "identifier_type")
    @Enumerated(EnumType.STRING)
    private IdentifierType identifierType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "password")
    private String password;

    // Eager va a ser el ManyToOne
    // El OneToMany permite la comunicación de uno a muchos, no hace la unión y por tanto se recomienda que sea Lazy
    // Usa @JsonIgnore para omitir valores OneToMany y evitar circular dependencies
    //
}
