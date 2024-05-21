package ni.factorizacion.server.domain.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.InvitedUser;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class InvitedUserSimpleDto extends UserSimpleDto {
    private String email;
    private String imageUrl;

    static public InvitedUserSimpleDto from(InvitedUser user) {
        return InvitedUserSimpleDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .identifier(user.getIdentifier())
                .identifierType(user.getIdentifierType().name())

                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .build();
    }
}
