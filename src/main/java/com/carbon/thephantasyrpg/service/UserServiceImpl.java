package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.UserRegistrationDTO;
import com.carbon.thephantasyrpg.enums.Roles;
import com.carbon.thephantasyrpg.model.Role;
import com.carbon.thephantasyrpg.model.User;
import com.carbon.thephantasyrpg.repository.RoleRepository;
import com.carbon.thephantasyrpg.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * UserServiceImpl class for the User Service
 */
@Service
public class UserServiceImpl implements UserService {

    // This is a JPA entity manager
    @PersistenceContext
    private EntityManager entityManager;

    // These are the repositories for the User and Role entities
    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // Assuming you have a RoleRepository

    // This is a password encoder
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for the UserServiceImpl class
     * @param userRepository the UserRepository object
     * @param roleRepository the RoleRepository object
     * @param passwordEncoder the PasswordEncoder object
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Register a new user with the given UserRegistrationDTO object
     * @param registrationDto the UserRegistrationDTO object
     * @return the User object
     */
    @Transactional
    @Override
    public User registerUser(UserRegistrationDTO registrationDto) {

        // Check if the username is already in use
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already in use");
        }

        // Create a new User object
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        // Get the roles from the registration DTO and map them to the Role entities
        Set<Role> managedRoles = registrationDto.getRoleNames().stream()
                .map(Roles::valueOfRoleName)
                .map(enumRole -> roleRepository.findByName(enumRole.getRoleName())
                        .map(role -> entityManager.merge(role)) // Merge each role into the current persistence context
                        .orElseThrow(() -> new IllegalArgumentException("Role not found with name: " + enumRole.getRoleName())))
                .collect(Collectors.toSet());

        // Set the roles for the user
        user.setRoles(new ArrayList<>(managedRoles));

        // Save the user
        return userRepository.save(user);
    }

}
