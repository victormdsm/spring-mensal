package com.mensal.project.mapper;

import com.mensal.project.dto.eventdto.EventDto;
import com.mensal.project.dto.eventdto.EventDtoIntegration;
import com.mensal.project.dto.eventdto.ResponseEventDto;
import com.mensal.project.dto.eventdto.UpdateEventDto;
import com.mensal.project.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EventMapper {

    @Mapping(source = "dto.id", target = "user.id")
    Event toEntity(EventDto dto);
    ResponseEventDto toDto(Event event);
    Event toEntityUpdate(UpdateEventDto dto);
    @Mapping(source = "id", target = "id")
    Event toEntityIntegration(EventDtoIntegration dto);
}
