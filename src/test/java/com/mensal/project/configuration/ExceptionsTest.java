package com.mensal.project.configuration;


import com.mensal.project.configuration.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ExceptionsTest {

    @Test
    @DisplayName("Test EntityNotFoundException")
    void testEntityNotFoundException() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            throw new EntityNotFoundException("Entity not found");
        });
        assertEquals("Entity not found", exception.getMessage());
    }

    @Test
    @DisplayName("Test BusinessException")
    void testBusinessException() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            throw new BusinessException("Business rule violated");
        });
        assertEquals("Business rule violated", exception.getMessage());
    }

    @Test
    @DisplayName("Test MailSendException")
    void testMailSendException() {
        MailSendException exception = assertThrows(MailSendException.class, () -> {
            throw new MailSendException("Email sending failed");
        });
        assertEquals("Email sending failed", exception.getMessage());
    }

    @Test
    @DisplayName("Test UnauthorizedException")
    void testUnauthorizedException() {
        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            throw new UnauthorizedException("Unauthorized access");
        });
        assertEquals("Unauthorized access", exception.getMessage());
    }

    @Test
    @DisplayName("Test UniqueMailException")
    void testUniqueMailException() {
        UniqueMailException exception = assertThrows(UniqueMailException.class, () -> {
            throw new UniqueMailException("Email already in use");
        });
        assertEquals("Email already in use", exception.getMessage());
    }

}
