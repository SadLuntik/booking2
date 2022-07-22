package com.sberschool.booking.mapper;

import com.sberschool.booking.dto.RoleDTO;
import com.sberschool.booking.entity.Role;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toRoleDTO(Role role);

    Role toRole(RoleDTO roleDto);

    List<RoleDTO> toRoleDTO(List<Role> listRole);

    List<Role> toRole(List<RoleDTO> listRoleDto);
}

