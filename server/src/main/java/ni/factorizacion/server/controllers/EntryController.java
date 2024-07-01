package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/entry/")
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
    public ResponseEntity<GeneralResponse<List<Entry>>> getOwnEntries() {
        Optional<RegisteredUser> user = authenticationService.getCurrentAuthenticatedUser();
        User userE = new User();
        if (user.isPresent()) {
            userE = user.get();
        }
        return GeneralResponse.ok("Entries found", entryService.getEntriesByUser(userE));
    }

    @GetMapping("/user/{identifier}")
    public ResponseEntity<GeneralResponse<List<Entry>>> getUserEntries(@PathVariable String identifier) {
        Optional<User> user = userService.findByIdentifier(identifier);
        if(user.isEmpty()){
            return GeneralResponse.error404("User not found");
        }
        User userEntity = user.get();
        return GeneralResponse.ok("Entries found", entryService.getEntriesByUser(userEntity));
    }

    @GetMapping("/own-residence")
    public ResponseEntity<GeneralResponse<List<Entry>>> getOwnResidenceEntries() {
        Optional<RegisteredUser> user = authenticationService.getCurrentAuthenticatedUser();
        Optional<Resident> resident = Optional.empty();
        Residence residenceE = new Residence();
        if(user.isEmpty()){
            return GeneralResponse.error404("User not found");
        }
        resident = residentService.findByIdentifier(user.get().getIdentifier());
        if(resident.isPresent()){
            residenceE = resident.get().getResidence();
        }
        return GeneralResponse.ok("Entries found", entryService.getEntriesByResidence(residenceE));
    }
    @GetMapping("/residence/{residenceS}")
    public ResponseEntity<GeneralResponse<List<Entry>>> getResidenceEntries(@PathVariable String residenceS) {
        UUID residence= UUID.fromString(residenceS);
        Optional<Residence> residenceO = residenceService.findById(residence);
        if(residenceO.isEmpty()){
            return GeneralResponse.error404("Residence not found");
        }

        return GeneralResponse.ok("Entries found", entryService.getEntriesByResidence(residenceO.get()));
    }
    
}
