package com.mensal.project.mapper;

import com.mensal.project.dto.InviteDto;
import com.mensal.project.entities.Invitation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EventMapper.class})
public interface InviteMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "event", target = "event")
    Invitation toEntity (InviteDto dto);
}
