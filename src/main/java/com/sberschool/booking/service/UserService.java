package com.sberschool.booking.service;

import com.sberschool.booking.dto.UserRegistrationDTO;
import com.sberschool.booking.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService extends UserDetailsService {

    User save(UserRegistrationDTO registrationDTO);
    String getUserName();
}
