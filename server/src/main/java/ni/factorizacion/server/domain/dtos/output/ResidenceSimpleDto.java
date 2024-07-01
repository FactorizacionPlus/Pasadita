package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.Residence;

import java.util.UUID;

@Data
@SuperBuilder
public class ResidenceSimpleDto {
    private UUID uuid;
    private String description;
    private Integer maxHabitants;

    static public ResidenceSimpleDto from(Residence residence) {
        return ResidenceSimpleDto.builder()
                .uuid(residence.getUuid())
                .description(residence.getDescription())
                .maxHabitants(residence.getMaxHabitants())
                .build();
    }
}
