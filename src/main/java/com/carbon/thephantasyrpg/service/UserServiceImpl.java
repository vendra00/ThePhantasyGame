package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.UserRegistrationDTO;
import com.carbon.thephantasyrpg.enums.Roles;
import com.carbon.thephantasyrpg.model.Role;
import com.carbon.thephantasyrpg.model.User;
import com.carbon.thephantasyrpg.repository.RoleRepository;
import com.carbon.thephantasyrpg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // Assuming you have a RoleRepository
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User registerUser(UserRegistrationDTO registrationDto) {
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already in use");
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Set<Role> roles = registrationDto.getRoleNames().stream()
                // Use the enum to validate and resolve role names
                .map(Roles::valueOfRoleName)
                // Assuming Role entity has a constructor that accepts roleName (adjust as necessary)
                // Or lookup the role by name if it needs to match an existing database record
                .map(enumRole -> roleRepository.findByName(enumRole.getRoleName())
                        .orElseThrow(() -> new IllegalArgumentException("Role not found with name: " + enumRole.getRoleName())))
                .collect(Collectors.toSet());

        user.setRoles(new ArrayList<>(roles));

        return userRepository.save(user);
    }

}
