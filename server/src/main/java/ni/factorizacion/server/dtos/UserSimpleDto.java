package ni.factorizacion.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ni.factorizacion.server.entities.User;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserSimpleDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String identifier;

    static public UserSimpleDto from(User user) {
        return new UserSimpleDto(user.getUuid(), user.getFirstName(), user.getLastName(), user.getIdentifier());
    }
}
