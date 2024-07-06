package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.Terminal;

import java.util.UUID;

@Data
@SuperBuilder
public class TerminalSimpleDto {
    private UUID uuid;
    private String type;

    static public TerminalSimpleDto from(Terminal terminal) {
        return TerminalSimpleDto.builder()
                .uuid(terminal.getUuid())
                .type(terminal.getType().toString())
                .build();

    }
}



