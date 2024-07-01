package ni.factorizacion.server.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.server.domain.entities.Entry;
import ni.factorizacion.server.domain.entities.TerminalType;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@SuperBuilder
public class EntrySimpleDto {
    private UUID uuid;

    private String accessDate;
    private String description;

    private UserSimpleDto user;
    private TerminalType terminal;
    private ResidenceSimpleDto residence;

    public static EntrySimpleDto from(Entry entry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return EntrySimpleDto.builder()
                .uuid(entry.getUuid())
                .accessDate(entry.getAccessDate().format(formatter))
                .description(entry.getDescription())
                .user(UserSimpleDto.from(entry.getUser()))
                .terminal(entry.getTerminal().getType())
                .residence(entry.getResidence() != null ? ResidenceSimpleDto.from(entry.getResidence()) : null)
                .build();
    }
}
