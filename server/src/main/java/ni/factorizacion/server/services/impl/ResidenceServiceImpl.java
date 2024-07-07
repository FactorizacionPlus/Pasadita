package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.dtos.input.SaveResidenceDto;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;
import ni.factorizacion.server.domain.entities.Status;
import ni.factorizacion.server.repositories.ResidenceRepository;
import ni.factorizacion.server.repositories.ResidentRepository;
import ni.factorizacion.server.services.ResidenceService;
import ni.factorizacion.server.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResidenceServiceImpl implements ResidenceService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private ResidenceRepository repository;

    public List<Residence> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Residence> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    @Override
    public Optional<Residence> findById(UUID uuid) {
        return repository.findById(uuid);
    }


    @Override
    public void saveResidence(SaveResidenceDto dto) throws ControlException {
        Optional<Residence> found = this.findByDescription(dto.getDescription());
        if (found.isPresent()) {
            throw new ControlException(HttpStatus.CONFLICT, "Residence already exists");
        }
        Residence residence = new Residence();
        residence.setMaxHabitants(dto.getMaxHabitants());
        residence.setDescription(dto.getDescription());
        residence.setStatus(Status.ACTIVE);
        repository.save(residence);
    }

    @Override
    public void updateResidence(String uuid, SaveResidenceDto dto) throws ControlException {
        Optional<Residence> found = repository.findById(UUID.fromString(uuid));
        if (found.isEmpty()) {
            throw new ControlException(HttpStatus.CONFLICT, "Residence does not exist");
        }
        Residence residence = found.get();
        residence.setMaxHabitants(dto.getMaxHabitants());
        residence.setDescription(dto.getDescription());
        repository.save(residence);
    }

    @Override
    public void removeResidence(String uuid) throws ControlException {
        boolean exist = repository.existsById(UUID.fromString(uuid));
        if (!exist) {
            throw new IllegalStateException(
                    "Residence with the id +" + uuid + " does not exist");
        }
        repository.deleteById(UUID.fromString(uuid));
    }

    @Override
    public void assignResidenteToResidencia(Resident resident, Residence residence) throws ControlException {
        if (residence.getResidents().size() >= residence.getMaxHabitants()) {
            throw new ControlException(HttpStatus.CONFLICT, "The residence reached the maximum number of residents allowed");
        }

        residence.getResidents().add(resident);
        repository.save(residence);
    }
}
