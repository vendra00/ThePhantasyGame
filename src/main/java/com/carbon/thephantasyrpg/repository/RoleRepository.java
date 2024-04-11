package com.carbon.thephantasyrpg.repository;

import com.carbon.thephantasyrpg.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RoleRepository interface for the Role Repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find a role by its name
     * @param name the name of the role
     * @return the role with the given name
     */
    Optional<Role> findByName(String name);
}
