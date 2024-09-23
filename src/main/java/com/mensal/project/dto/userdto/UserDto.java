package com.mensal.project.dto.userdto;

import jakarta.validation.constraints.*;


public record UserDto(
        @NotBlank @Email (message = "email deve ser válido") String email,
        @NotBlank(message = "A senha não pode estar vazia") String password,
        @Pattern(regexp = "^\\+55 \\d{2} 9\\d{4}-\\d{4}$",
                message = "Número de celular deve estar no formato +55 XX 9XXXX-XXXX")
        String phone,
        @NotBlank String name) {
}
