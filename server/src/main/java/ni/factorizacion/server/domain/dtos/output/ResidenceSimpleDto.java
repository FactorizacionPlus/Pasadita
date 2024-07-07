package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.Residence;
import org.hibernate.Hibernate;

import java.util.UUID;

@Data
@SuperBuilder
public class ResidenceSimpleDto {
    private UUID uuid;
    private String description;
    private int habitants;
    private int maxHabitants;

    private int entryCount;

    static public ResidenceSimpleDto from(Residence residence) {
        return ResidenceSimpleDto.builder()
                .uuid(residence.getUuid())
                .description(residence.getDescription())
                .habitants(Hibernate.size(residence.getResidents()))
                .maxHabitants(residence.getMaxHabitants())
                .entryCount(Hibernate.size(residence.getEntries()))
                .build();
    }
}
