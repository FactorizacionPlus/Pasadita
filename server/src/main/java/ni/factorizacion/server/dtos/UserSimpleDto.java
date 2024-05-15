package ni.factorizacion.server.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.entities.User;

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
                .identifierType(user.getIdentifierType().name())
                .build();
    }
}
