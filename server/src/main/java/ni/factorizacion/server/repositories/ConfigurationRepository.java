package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConfigurationRepository extends JpaRepository<Configuration, UUID> {
    Configuration findFirstBy();
}
