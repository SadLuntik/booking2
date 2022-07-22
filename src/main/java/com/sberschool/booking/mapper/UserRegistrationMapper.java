package com.sberschool.booking.mapper;

import com.sberschool.booking.dto.UserRegistrationDTO;
import com.sberschool.booking.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {
    @Mapping(target = "userRoles", source = "roles")
    UserRegistrationDTO toUserRegistrationDTO(User user);

    @Mapping(target = "roles", source = "userRoles")
    User toUserRegistration(UserRegistrationDTO userRegistrationDto);

    List<UserRegistrationDTO> toUserRegistrationDTO(List<User> userList);

    List<User> toUserRegistration(List<UserRegistrationDTO> userRegistrationDtoList);
}

