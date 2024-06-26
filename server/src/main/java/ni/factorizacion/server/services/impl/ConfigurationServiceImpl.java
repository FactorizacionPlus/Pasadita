package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.Configuration;
import ni.factorizacion.server.repositories.ConfigurationRepository;
import ni.factorizacion.server.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Override
    public Configuration getConfiguration() {
        return configurationRepository.findFirstBy();
    }

    @Override
    public void updateConfiguration(Configuration configuration) {
        configurationRepository.save(configuration);
    }

    @Override
    public void resetConfiguration() {
        Configuration configuration = configurationRepository.findFirstBy();
        configuration.setTimeframe(1800L);
        configuration.setQrExpiration(600L);
        configurationRepository.save(configuration);
    }
    @Override
    public void saveConfiguration(Configuration configuration) {
        configurationRepository.save(configuration);
    }
}
