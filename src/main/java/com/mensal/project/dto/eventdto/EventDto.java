package com.mensal.project.dto.eventdto;

import com.mensal.project.dto.userdto.UserDto;

import java.time.LocalDateTime;

public record EventDto(String title, String description, LocalDateTime startDateTime, LocalDateTime endDateTime, String location, int capacity, UserDto dto) {
}
