package com.mensal.project.dto.userdto;

import com.mensal.project.entities.enums.UserType;

public record SetRoleDto(Long id, UserType role) {
}
