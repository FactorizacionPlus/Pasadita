package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Permission;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Page<Permission> findAllByResidence(Residence residence, Pageable pageable);

    Page<Permission> findAllByResident(Resident resident, Pageable pageable);

    Page<Permission> findAllByInvitedUser(InvitedUser invitedUser, Pageable pageable);

    @Query("SELECT p FROM Permission p WHERE p.startDate <= :date AND p.endDate + :timeframe second >= :date AND p.invitedUser = :user")
    Optional<Permission> findByUserAndDate(@Param("user") InvitedUser user, @Param("date") LocalDateTime date, @Param("timeframe") Long timeframe);
}
