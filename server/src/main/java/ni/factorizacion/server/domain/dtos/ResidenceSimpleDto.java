package ni.factorizacion.server.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.Residence;

@Data
@SuperBuilder
public class ResidenceSimpleDto {
    private String description;
    private Integer maxHabitans;

    static public ResidenceSimpleDto from(Residence residence){
        return ResidenceSimpleDto.builder()
                .description(residence.getDescription())
                .maxHabitans(residence.getMaxHabitants())
                .build();
    }
}
