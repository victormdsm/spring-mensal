package com.mensal.project.dto;

import com.mensal.project.dto.eventdto.EventDtoIntegration;
import com.mensal.project.entities.Event;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteDto(
        @Email String email,
        EventDtoIntegration event
) {
}
