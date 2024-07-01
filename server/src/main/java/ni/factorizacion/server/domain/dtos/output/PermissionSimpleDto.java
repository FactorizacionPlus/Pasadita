package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.Permission;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@SuperBuilder
public class PermissionSimpleDto {
    private UUID uuid;

    private String creationDate;
    private String startDate;
    private String endDate;
    private Boolean authorized;

    private RegisteredUserSimpleDto invitedUser;
    private ResidentSimpleDto resident;
    private ResidenceSimpleDto residence;

    public static PermissionSimpleDto from(Permission permission) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return PermissionSimpleDto.builder()
                .uuid(permission.getUuid())
                .creationDate(permission.getCreationDate().format(formatter))
                .startDate(permission.getStartDate().format(formatter))
                .endDate(permission.getEndDate().format(formatter))
                .authorized(permission.getAuthorized())
                .invitedUser(RegisteredUserSimpleDto.from(permission.getInvitedUser()))
                .resident(ResidentSimpleDto.from(permission.getResident()))
                .residence(ResidenceSimpleDto.from(permission.getResidence()))
                .build();
    }
}
