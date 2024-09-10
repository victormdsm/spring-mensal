package com.mensal.project.mapper;

import com.mensal.project.dto.eventdto.EventDto;
import com.mensal.project.dto.eventdto.ResponseEventDto;
import com.mensal.project.dto.eventdto.UpdateEventDto;
import com.mensal.project.entities.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEntity(EventDto dto);
    ResponseEventDto toDto(Event event);
    Event toEntityUpdate(UpdateEventDto dto);
}
