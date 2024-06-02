package ni.factorizacion.server.repositories;

import ni.factorizacion.server.domain.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}
