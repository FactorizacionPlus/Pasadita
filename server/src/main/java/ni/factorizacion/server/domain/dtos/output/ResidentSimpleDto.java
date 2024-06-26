package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.Resident;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ResidentSimpleDto extends RegisteredUserSimpleDto {
    private ResidenceSimpleDto residence;

    static public ResidentSimpleDto from(Resident resident) {
        return ResidentSimpleDto.builder()
                .uuid(resident.getUuid())
                .firstName(resident.getFirstName())
                .lastName(resident.getLastName())
                .identifier(resident.getIdentifier())
                .identifierType(resident.getIdentifierType().name())

                .email(resident.getEmail())
                .imageUrl(resident.getImageUrl())

                .role(resident.getAuthorities().iterator().next().getAuthority())
                .residence(resident.getResidence() != null ? ResidenceSimpleDto.from(resident.getResidence()) : null)
                .build();
    }
}
