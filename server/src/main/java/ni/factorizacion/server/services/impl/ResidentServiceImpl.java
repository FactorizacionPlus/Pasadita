package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.entities.Resident;
import ni.factorizacion.server.repositories.ResidentRepository;
import ni.factorizacion.server.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService {
    @Autowired
    private ResidentRepository repository;

    @Override
    public List<Resident> getAll() {
        return repository.findAll();
    }
}
