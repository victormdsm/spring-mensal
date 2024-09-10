package com.mensal.project.dto.eventdto;

import java.time.LocalDateTime;

public record ResponseEventDto(Long id,String title, String description, LocalDateTime startDateTime, LocalDateTime endDateTime, String location, int capacity) {
}
