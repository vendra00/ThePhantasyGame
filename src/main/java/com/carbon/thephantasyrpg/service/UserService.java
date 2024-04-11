package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.UserRegistrationDTO;
import com.carbon.thephantasyrpg.model.User;

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
}
