package com.mensal.project.mapper;

import com.mensal.project.dto.usereventdto.ResponseUserEventDto;
import com.mensal.project.dto.usereventdto.UpdateUserEventDto;
import com.mensal.project.dto.usereventdto.UserEventDto;
import com.mensal.project.entities.UserEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEventMapper {

    UserEvent toEntity(UserEventDto dto);
    ResponseUserEventDto toDto(UserEvent userEvent);
    UserEvent toEntityUpdate(UpdateUserEventDto dto);
}
