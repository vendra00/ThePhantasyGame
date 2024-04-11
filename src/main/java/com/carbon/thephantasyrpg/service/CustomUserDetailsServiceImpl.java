package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.model.Role;
import com.carbon.thephantasyrpg.model.User;
import com.carbon.thephantasyrpg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * CustomUserDetailsServiceImpl class for the Custom User Details Service
 */
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    // This is the UserRepository
    private final UserRepository userRepository;

    /**
     * Constructor for the CustomUserDetailsServiceImpl class
     * @param userRepository the UserRepository object
     */
    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Here, you should map your User entity to the UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                convertRolesToAuthorities(user.getRoles())); // Implement this method to convert your roles to Spring Security authorities
    }

    /**
     * Convert a collection of Role objects to a collection of GrantedAuthority objects
     * @param roles the collection of Role objects
     * @return the collection of GrantedAuthority objects
     */
    private Collection<? extends GrantedAuthority> convertRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
