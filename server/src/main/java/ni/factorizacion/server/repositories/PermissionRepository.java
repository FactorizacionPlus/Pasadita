package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    @Query("SELECT p FROM Permission p WHERE p.startDate >= :date AND p.endDate <= :date AND p.invitedUser = :user")
    Permission findByUserAndDate(@Param("user") InvitedUser user, @Param("date") LocalDateTime date);
}
