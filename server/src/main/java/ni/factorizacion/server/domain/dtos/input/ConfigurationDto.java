package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfigurationDto {
    @NotNull
    private Long timeframe;
    @NotNull
    private Long qrExpiration;
}
