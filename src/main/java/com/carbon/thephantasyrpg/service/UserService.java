package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.UserRegistrationDTO;
import com.carbon.thephantasyrpg.model.User;

public interface UserService {
    User registerUser(UserRegistrationDTO registrationDto);
}
