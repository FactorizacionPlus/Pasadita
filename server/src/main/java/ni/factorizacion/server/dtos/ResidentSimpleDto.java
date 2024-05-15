package ni.factorizacion.server.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.entities.Residence;
import ni.factorizacion.server.entities.Resident;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResidentSimpleDto extends InvitedUserSimpleDto {
    private Integer role;
    private Residence residence;

    static public ResidentSimpleDto from(Resident resident) {
        return ResidentSimpleDto.builder()
                .uuid(resident.getUuid())
                .firstName(resident.getFirstName())
                .lastName(resident.getLastName())
                .identifier(resident.getIdentifier())
                .identifierType(resident.getIdentifierType().name())

                .email(resident.getEmail())
                .imageUrl(resident.getImageUrl())

                .role(resident.getRole())
                .residence(resident.getResidence())
                .build();
    }
}
