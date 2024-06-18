package ni.factorizacion.server.domain.dtos;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveResidenceDto {
    @NotNull
    private Integer maxHabitants;
    @NotEmpty
    private String description;
}
