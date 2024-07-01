package ni.factorizacion.server.utils;

import java.util.Optional;
import java.util.UUID;

public class UUIDUtils {
    static public Optional<UUID> fromString(String uuid) {
        try {
            return Optional.of(UUID.fromString(uuid));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
