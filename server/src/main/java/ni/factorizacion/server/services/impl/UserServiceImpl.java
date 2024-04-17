package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<String> getAll() {
        return List.of("User1", "User2", "User3", "User4");
    }
}
