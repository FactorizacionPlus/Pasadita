package ni.factorizacion.server.domain.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TerminalValidateQRDto extends TerminalLoginDto {
    @NotEmpty
    private String tokenContent;
}
