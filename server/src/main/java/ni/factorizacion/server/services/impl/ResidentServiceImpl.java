package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.dtos.input.SaveResidentDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.repositories.ResidentRepository;
import ni.factorizacion.server.services.ResidentService;
import ni.factorizacion.server.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {
    @Autowired
    private ResidentRepository repository;

    @Override
    public Page<Resident> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Resident> findAllByResidence(Residence residence, Pageable pageable) {
        return repository.findAllByResidence(residence, pageable);
    }

    @Override
    public Optional<Resident> findByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier);
    }

    @Override
    public long getCount() {
        return repository.count();
    }

    @Override
    public void saveUser(SaveResidentDto dto) throws ControlException {
        Optional<Resident> found = repository.findByIdentifier(dto.getIdentifier());
        if (found.isPresent()) {
            throw new ControlException(HttpStatus.CONFLICT, "User already exists");
        }
        Resident user = new Resident();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        user.setIdentifier(dto.getIdentifier());
        user.setIdentifierType(dto.getIdentifierType());

        user.setEmail(dto.getEmail());
        user.setImageUrl(dto.getImageUrl());

        user.setStatus(Status.ACTIVE);
        user.setRole(dto.getRole());

        repository.save(user);
    }

    @Override
    public void updateUser(SaveResidentDto dto) throws ControlException {

    }

    @Override
    public void removeUser(String identifier) throws ControlException {

    }

    @Override
    public Optional<Resident> createFrom(RegisteredUser user) {
        if (user.getClass().equals(Resident.class)) {
            return Optional.empty();
        }
        Resident resident = new Resident();

        resident.setUuid(user.getUuid());
        resident.setFirstName(user.getFirstName());
        resident.setLastName(user.getLastName());

        resident.setIdentifier(user.getIdentifier());
        resident.setIdentifierType(user.getIdentifierType());

        resident.setEmail(user.getEmail());
        resident.setImageUrl(user.getImageUrl());

        resident.setStatus(Status.ACTIVE);
        resident.setRole(ResidentRole.NORMAL);

        return Optional.of(resident);
    }

    @Override
    public void changeResidentRole(Resident resident, ResidentRole role) {
        resident.setRole(role);
        repository.save(resident);
    }

    @Override
    public void save(Resident resident) {
        repository.save(resident);
    }
}
