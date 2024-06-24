package ni.factorizacion.server.domain.entities;

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

    // Expiration time (in seconds) for QR codes
    private Long qrExpiration;
}
