package ni.factorizacion.server.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private RegisteredUser user;

    public Token(String content, RegisteredUser user) {
        super();
        this.content = content;
        this.user = user;
        this.timestamp = Date.from(Instant.now());
        this.active = true;
    }
}
