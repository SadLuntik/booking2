package com.sberschool.booking.service.impl;

import com.sberschool.booking.dto.RoleDTO;
import com.sberschool.booking.dto.UserRegistrationDTO;
import com.sberschool.booking.entity.Role;
import com.sberschool.booking.entity.User;
import com.sberschool.booking.mapper.UserRegistrationMapper;
import com.sberschool.booking.repository.UserRepository;
import com.sberschool.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRegistrationMapper userRegistrationMapper;

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        try {
            User user = new User(userRegistrationDTO.getLogin(),
                    passwordEncoder.encode(userRegistrationDTO.getPassword()),
                    List.of(new Role("ROLE_ADMIN")));
            return userRepository.save(user);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails userDetails) {
            username = (userDetails).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRegistrationDTO user = userRegistrationMapper.toUserRegistrationDTO(userRepository.findByLogin(username));
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getUserRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleDTO> roleDTOS) {
        return roleDTOS.stream().map(roleDTO -> new SimpleGrantedAuthority(roleDTO.getName())).toList();
    }
}

