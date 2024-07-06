package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class TerminalValidateQRDto extends TerminalLoginDto {
    @NotNull
    private UUID tokenContent;
}
