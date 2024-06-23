package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.Residence;

@Data
@SuperBuilder
public class ResidenceSimpleDto {
    private String description;
    private Integer maxHabitants;

    static public ResidenceSimpleDto from(Residence residence){
        return ResidenceSimpleDto.builder()
                .description(residence.getDescription())
                .maxHabitants(residence.getMaxHabitants())
                .build();
    }
}
