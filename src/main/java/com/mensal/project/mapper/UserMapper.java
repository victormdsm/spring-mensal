package com.mensal.project.mapper;

import com.mensal.project.dto.userdto.ResponseUserDto;
import com.mensal.project.dto.userdto.UpdateUserDto;
import com.mensal.project.dto.userdto.UserDto;
import com.mensal.project.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(source = "email", target = "email")
    User toEntity(UserDto dto);
    ResponseUserDto toDto(User user);
    User toEntityUpdate(UpdateUserDto dto);
}
