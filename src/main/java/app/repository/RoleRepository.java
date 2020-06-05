package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

