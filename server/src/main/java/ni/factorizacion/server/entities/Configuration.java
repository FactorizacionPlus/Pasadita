package ni.factorizacion.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Configuration {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    // Timeframe for allowing entries after permission has expired
    private Integer timeframe;
}
