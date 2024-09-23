package com.mensal.project.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UpdateUserDto(String name,
                            @Pattern(regexp = "^\\+55 \\d{2} 9\\d{4}-\\d{4}$",
                                    message = "Número de celular deve estar no formato +55 XX 9XXXX-XXXX")
                            String phone,
                            @Email String email) {
}
