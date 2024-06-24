package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.User;

import java.util.UUID;

@Data
@SuperBuilder
public class UserSimpleDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String identifier;
    private String identifierType;

    static public UserSimpleDto from(User user) {
        return UserSimpleDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .identifier(user.getIdentifier())
                .identifierType(user.getIdentifierType() != null ? user.getIdentifierType().name() : null)
                .build();
    }
}
