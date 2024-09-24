package com.mensal.project.dto;

import com.mensal.project.dto.userdto.ResponseUserDto;
import com.mensal.project.dto.userdto.UpdateUserDto;
import com.mensal.project.dto.userdto.UserDto;
import com.mensal.project.dto.userdto.UserDtoIntegration;
import com.mensal.project.entities.enums.UserType;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDtoTest {

    @Test
    void testResponseUserDto() {
        ResponseUserDto responseUserDto = new ResponseUserDto(1L, "test@example.com", "+55 12 91234-5678", "John Doe", UserType.PARTICIPANT);

        assertEquals(1L, responseUserDto.id());
        assertEquals("test@example.com", responseUserDto.email());
        assertEquals("+55 12 91234-5678", responseUserDto.phone());
        assertEquals("John Doe", responseUserDto.name());
    }

    @Test
    void testUpdateUserDto() {
        UpdateUserDto updateUserDto = new UpdateUserDto("John Doe", "+55 12 91234-5678", "test@example.com");

        assertEquals("John Doe", updateUserDto.name());
        assertEquals("+55 12 91234-5678", updateUserDto.phone());
        assertEquals("test@example.com", updateUserDto.email());
    }

    @Test
    void testUserDtoIntegration() {
        UserDtoIntegration userDtoIntegration = new UserDtoIntegration(1L);

        assertEquals(1L, userDtoIntegration.id());
    }

    @Test
    void testUserDto() {
        UserDto userDto = new UserDto("test@example.com", "ValidPass123!", "+55 12 91234-5678", "John Doe");

        assertEquals("test@example.com", userDto.email());
        assertEquals("ValidPass123!", userDto.password());
        assertEquals("+55 12 91234-5678", userDto.phone());
        assertEquals("John Doe", userDto.name());
    }
}
