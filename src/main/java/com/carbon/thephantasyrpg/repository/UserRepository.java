package com.carbon.thephantasyrpg.repository;

import com.carbon.thephantasyrpg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository interface for the User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find a user by their username
     * @param username the username of the user
     * @return the user with the given username
     */
    Optional<User> findByUsername(String username);
}
