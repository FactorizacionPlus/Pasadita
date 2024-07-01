package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Permission;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    List<Permission> findAllByResidence(Residence residence);

    List<Permission> findAllByResident(Resident resident);

    List<Permission> findAllByInvitedUser(InvitedUser invitedUser);

    @Query("SELECT p FROM Permission p WHERE p.startDate >= :date AND p.endDate <= :date AND p.invitedUser = :user")
    Optional<Permission> findByUserAndDate(@Param("user") InvitedUser user, @Param("date") LocalDateTime date);
}
