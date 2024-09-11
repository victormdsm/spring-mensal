package com.mensal.project.dto.usereventdto;

import com.mensal.project.dto.eventdto.EventDto;
import com.mensal.project.dto.userdto.UserDto;
import com.mensal.project.entities.enums.Status;

import java.time.LocalDateTime;

public record UserEventDto(LocalDateTime registrationDateTime, Status participantStatus, UserDto user, EventDto event) {
}
