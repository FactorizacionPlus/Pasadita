package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.Configuration;

public interface ConfigurationService {
    Configuration getConfiguration();

    void updateConfiguration(Configuration configuration);

    void resetConfiguration();

    void saveConfiguration(Configuration configuration);
}
