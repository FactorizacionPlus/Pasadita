package ni.factorizacion.server.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Resident extends InvitedUser {
    @NotNull
    @Enumerated(EnumType.STRING)
    private ResidentRole role;

    @ManyToOne
    private Residence residence;

    @Override
    @ElementCollection
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = switch (role) {
            case NORMAL -> new SimpleGrantedAuthority("ROLE_RESIDENT");
            case SUDO -> new SimpleGrantedAuthority("ROLE_RESIDENT_SUDO");
        };
        return List.of(authority);
    }
}
