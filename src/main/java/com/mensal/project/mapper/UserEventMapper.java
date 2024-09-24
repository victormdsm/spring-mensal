package com.mensal.project.mapper;

import com.mensal.project.dto.usereventdto.ResponseUserEventDto;
import com.mensal.project.dto.usereventdto.UpdateUserEventDto;
import com.mensal.project.dto.usereventdto.UserEventDto;
import com.mensal.project.entities.User;
import com.mensal.project.entities.UserEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, EventMapper.class})
public interface UserEventMapper {

    @Mapping(source = "user.id", target = "users.id")
    @Mapping(source = "event.id", target = "event.id")
    UserEvent toEntity(UserEventDto dto);
    ResponseUserEventDto toDto(UserEvent userEvent);
    UserEvent toEntityUpdate(UpdateUserEventDto dto);
}
