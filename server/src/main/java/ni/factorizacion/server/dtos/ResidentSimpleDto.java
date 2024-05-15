package ni.factorizacion.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ni.factorizacion.server.entities.Residence;
import ni.factorizacion.server.entities.Resident;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ResidentSimpleDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String identifier;
    private String identifierType;
    private String email;
    private String imageUrl;
    private Integer role;
    private Residence residence;

    static public ResidentSimpleDto from(Resident resident) {
        return new ResidentSimpleDto(
                resident.getUuid(),
                resident.getFirstName(),
                resident.getLastName(),
                resident.getIdentifier(),
                resident.getIdentifierType().name(),
                resident.getEmail(),
                resident.getImageUrl(),
                resident.getRole(),
                resident.getResidence()
        );
    }
}
