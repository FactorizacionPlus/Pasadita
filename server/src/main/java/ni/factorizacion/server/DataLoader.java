package ni.factorizacion.server;

import ni.factorizacion.server.domain.dtos.input.SaveResidentDto;
import ni.factorizacion.server.domain.dtos.input.SaveUserDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.repositories.TerminalRepository;
import ni.factorizacion.server.services.ConfigurationService;
import ni.factorizacion.server.services.ResidentService;
import ni.factorizacion.server.services.UserService;
import ni.factorizacion.server.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    UserService userService;

    @Autowired
    ResidentService residentService;

    @Autowired
    TerminalRepository terminalRepository;

    public void populateResidents() throws ControlException {
        if (residentService.getCount() > 0) {
            return;
        }
        SaveResidentDto dto = new SaveResidentDto();

        dto.setEmail("00543224@uca.edu.sv");
        dto.setFirstName("Jezer");
        dto.setLastName("Mejía");
        dto.setIdentifier("C0120001");
        dto.setIdentifierType(IdentifierType.PASSPORT);
        dto.setRole(ResidentRole.SUDO);
        residentService.saveUser(dto);

        dto.setEmail("00544224@uca.edu.sv");
        dto.setFirstName("David");
        dto.setLastName("Quintanilla");
        dto.setIdentifier("C0120002");
        dto.setIdentifierType(IdentifierType.PASSPORT);
        dto.setRole(ResidentRole.SUDO);
        residentService.saveUser(dto);

        dto.setEmail("00543824@uca.edu.sv");
        dto.setFirstName("David");
        dto.setLastName("Ponce");
        dto.setIdentifier("C0120001");
        dto.setIdentifierType(IdentifierType.PASSPORT);
        dto.setRole(ResidentRole.NORMAL);
        residentService.saveUser(dto);

        dto.setEmail("00542724@uca.edu.sv");
        dto.setFirstName("Leo");
        dto.setLastName("Corea");
        dto.setIdentifier("C0120001");
        dto.setIdentifierType(IdentifierType.PASSPORT);
        dto.setRole(ResidentRole.NORMAL);
        residentService.saveUser(dto);

        dto.setEmail("00542724@uca.edu.sv");
        dto.setFirstName("Marcelo");
        dto.setLastName("Rivera");
        dto.setIdentifier("C0120005");
        dto.setIdentifierType(IdentifierType.PASSPORT);
        dto.setRole(ResidentRole.NORMAL);
        residentService.saveUser(dto);
    }

    public void populateAnonymousUsers() throws ControlException {
        if (userService.getCountAnonymous() > 0) {
            return;
        }
        SaveUserDto dto = new SaveUserDto();
        User user;

        dto.setFirstName("Juan");
        dto.setLastName("Pérez");
        dto.setIdentifier("C0110001");
        dto.setIdentifierType(IdentifierType.DUI);
        user = userService.createFrom(dto).get();
        userService.save(user);

        dto.setFirstName("Juan Daniel");
        dto.setLastName("Treminio");
        dto.setIdentifier("C0110002");
        dto.setIdentifierType(IdentifierType.DUI);
        user = userService.createFrom(dto).get();
        userService.save(user);
    }

    public void populateTerminals() throws ControlException {
        if (terminalRepository.count() > 0) {
            return;
        }
        Terminal terminal;

        terminal = createTerminal(TerminalType.DOOR, "Usuario123.");
        terminalRepository.save(terminal);

        terminal = createTerminal(TerminalType.BARRIER, "Usuario123.");
        terminalRepository.save(terminal);

        terminal = createTerminal(TerminalType.MANUAL, "Usuario123.");
        terminalRepository.save(terminal);
    }

    private Terminal createTerminal(TerminalType type, String password) throws ControlException {
        Terminal terminal = new Terminal();
        terminal.setType(type);
        terminal.setPassword(password);
        return terminal;
    }

    public void populateConfiguration() {
        if (configurationService.getConfiguration() == null) {
            Configuration configuration = new Configuration();
            configuration.setTimeframe(1800L);
            configuration.setQrExpiration(600L);
            configurationService.saveConfiguration(configuration);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        populateConfiguration();
        populateResidents();
        populateAnonymousUsers();
        populateTerminals();
    }
}
