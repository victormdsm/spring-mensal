package com.mensal.project.dto.eventdto;

import com.mensal.project.dto.userdto.UserDtoIntegration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventDto(
        @NotBlank(message = "O titulo não pode ser vazio") String title,
        @NotBlank(message = "A descrição não pode ser vazia") String description,
        @NotNull(message = "A data e hora de inicio devem ser informadas") LocalDateTime startDateTime,
        @NotNull(message = "A data e hora de termino devem ser informadas") LocalDateTime endDateTime,
        @NotBlank(message = "a localização deve ser informada") String location,
        @NotNull(message = "a capacidade deve ser informada") int capacity,
        @NotNull(message = "O usuario deve ser informado") UserDtoIntegration dto) {
}
