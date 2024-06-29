package ni.factorizacion.server;

import ni.factorizacion.server.domain.entities.Configuration;
import ni.factorizacion.server.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    ConfigurationService configurationService;

    public void populateConfiguration(){
        if(configurationService.getConfiguration() == null){
            Configuration configuration = new Configuration();
            configuration.setTimeframe(1800L);
            configuration.setQrExpiration(600L);
            configurationService.saveConfiguration(configuration);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        populateConfiguration();
    }
}
