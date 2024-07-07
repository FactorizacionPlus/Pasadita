package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.output.EntrySimpleDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/entry")
public class EntryController {
    @Autowired
    EntryService entryService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ResidentService residentService;

    @Autowired
    ResidenceService residenceService;

    @GetMapping("/own")
    public ResponseEntity<GeneralResponse<Page<EntrySimpleDto>>> getOwnEntries(Pageable pageable) {
        Optional<RegisteredUser> user = authenticationService.getCurrentAuthenticatedUser();
        if (user.isEmpty()) {
            return GeneralResponse.error404("User not found");
        }
        Page<Entry> entries = entryService.getEntriesByUser(user.get(), pageable);
        Page<EntrySimpleDto> entrySimpleDtos = entries.map(EntrySimpleDto::from);

        return GeneralResponse.ok("Entries found", entrySimpleDtos);
    }

    @GetMapping("/user/{identifier}")
    public ResponseEntity<GeneralResponse<Page<EntrySimpleDto>>> getUserEntries(@PathVariable String identifier, Pageable pageable) {
        Optional<User> user = userService.findByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.error404("User not found");
        }
        Page<Entry> entries = entryService.getEntriesByUser(user.get(), pageable);
        Page<EntrySimpleDto> entrySimpleDtos = entries.map(EntrySimpleDto::from);

        return GeneralResponse.ok("Entries found", entrySimpleDtos);
    }

    @GetMapping("/own-residence")
    @PreAuthorize("hasRole('ROLE_RESIDENT')")
    public ResponseEntity<GeneralResponse<Page<EntrySimpleDto>>> getOwnResidenceEntries(Pageable pageable) {
        Optional<RegisteredUser> user = authenticationService.getCurrentAuthenticatedUser();
        if (user.isEmpty()) {
            return GeneralResponse.error404("User not found");
        }
        if (!user.get().getClass().equals(Resident.class)) {
            return GeneralResponse.error418("User is not a resident");
        }
        Resident resident = (Resident) user.get();

        if (resident.getResidence() == null) {
            return GeneralResponse.error404("Resident does not have a Residence");
        }

        Page<Entry> entries = entryService.getEntriesByResidence(resident.getResidence(), pageable);
        Page<EntrySimpleDto> entrySimpleDtos = entries.map(EntrySimpleDto::from);

        return GeneralResponse.ok("Entries found", entrySimpleDtos);
    }

    @GetMapping("/residence/{residenceS}")
    public ResponseEntity<GeneralResponse<Page<EntrySimpleDto>>> getResidenceEntries(@PathVariable String residenceS, Pageable pageable) {
        UUID uuid = UUID.fromString(residenceS);

        Optional<Residence> residence = residenceService.findById(uuid);
        if (residence.isEmpty()) {
            return GeneralResponse.error404("Residence not found");
        }

        Page<Entry> entries = entryService.getEntriesByResidence(residence.get(), pageable);
        Page<EntrySimpleDto> entrySimpleDtos = entries.map(EntrySimpleDto::from);

        return GeneralResponse.ok("Entries found", entrySimpleDtos);
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse<Page<EntrySimpleDto>>> getAllEntries(Pageable pageable) {
        Page<Entry> entries = entryService.findAll(pageable);
        if (entries.isEmpty()) {
            return GeneralResponse.ok("No entries found", Page.empty());
        }
        Page<EntrySimpleDto> entrySimpleDtos = entries.map(EntrySimpleDto::from);
        return GeneralResponse.ok("Found entries", entrySimpleDtos);
    }

}
