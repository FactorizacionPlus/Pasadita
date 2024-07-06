package ni.factorizacion.server.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID code;

    private String content;

    @Column(updatable = false)
    private Date timestamp;

    @NotNull
    private Boolean active;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TokenType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private RegisteredUser user;

    public Token(String content, RegisteredUser user, TokenType type) {
        super();
        this.content = content;
        this.user = user;
        this.timestamp = Date.from(Instant.now());
        this.active = true;
        this.type = type;
    }
}
