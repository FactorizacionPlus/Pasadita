package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.dtos.ResidentSimpleDto;
import ni.factorizacion.server.dtos.SaveResidentDto;
import ni.factorizacion.server.dtos.SaveUserDto;
import ni.factorizacion.server.entities.Resident;
import ni.factorizacion.server.entities.Status;
import ni.factorizacion.server.repositories.ResidentRepository;
import ni.factorizacion.server.services.ResidentService;
import ni.factorizacion.server.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {
    @Autowired
    private ResidentRepository repository;

    @Override
    public List<ResidentSimpleDto> getAll() {
        return repository.findAll().stream().map(ResidentSimpleDto::from).toList();
    }

    @Override
    public Optional<ResidentSimpleDto> findByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier).map(ResidentSimpleDto::from);
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
}
