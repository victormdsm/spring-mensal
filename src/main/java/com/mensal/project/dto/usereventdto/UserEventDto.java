package com.mensal.project.dto.usereventdto;

import com.mensal.project.dto.eventdto.EventDto;
import com.mensal.project.dto.eventdto.EventDtoIntegration;
import com.mensal.project.dto.userdto.UserDto;
import com.mensal.project.dto.userdto.UserDtoIntegration;
import com.mensal.project.entities.enums.Status;

import java.time.LocalDateTime;

public record UserEventDto(
        UserDtoIntegration user,
        EventDtoIntegration event) {
}
