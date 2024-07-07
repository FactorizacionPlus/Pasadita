package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import org.hibernate.Hibernate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class RegisteredUserSimpleDto extends UserSimpleDto {
    private String email;
    private String imageUrl;
    private String role;

    static public RegisteredUserSimpleDto from(RegisteredUser user) {
        return RegisteredUserSimpleDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .identifier(user.getIdentifier())
                .identifierType(user.getIdentifierType() != null ? user.getIdentifierType().name() : null)
                .entryCount(Hibernate.size(user.getEntries()))

                .email(user.getEmail())
                .role(user.getAuthorities().iterator().next().getAuthority())
                .imageUrl(user.getImageUrl())
                .build();
    }
}
