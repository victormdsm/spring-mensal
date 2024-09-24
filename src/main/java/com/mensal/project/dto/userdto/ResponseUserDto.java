package com.mensal.project.dto.userdto;

import com.mensal.project.entities.enums.UserType;

public record ResponseUserDto (Long id, String email, String phone, String name, UserType userType){
}
