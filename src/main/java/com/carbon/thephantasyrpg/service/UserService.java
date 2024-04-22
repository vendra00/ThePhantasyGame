package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.UserRegistrationDTO;
import com.carbon.thephantasyrpg.model.User;

import java.util.Optional;

/**
 * UserService interface for the User Service
 */
public interface UserService {
    /**
     * Register a new user with the given UserRegistrationDTO object
     * @param registrationDto the UserRegistrationDTO object
     * @return the User object
     */
    User registerUser(UserRegistrationDTO registrationDto);

    /**
     * Find a user by their username
     * @param username the username of the user
     * @return the user with the given username
     */
    Optional<User> findByUsername(String username);
}
