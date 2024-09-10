package com.mensal.project.dto.eventdto;

import java.time.LocalDateTime;

public record UpdateEventDto(String title, String description, int capacity, LocalDateTime startDateTime, LocalDateTime endDateTime, String location) {
}
