package com.sberschool.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private String login;
    private String password;
    private Collection<RoleDTO> userRoles;
}
