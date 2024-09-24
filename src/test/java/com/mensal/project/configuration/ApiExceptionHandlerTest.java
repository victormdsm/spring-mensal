package com.mensal.project.configuration;

import com.mensal.project.configuration.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ApiExceptionHandlerTest {

    private ApiExceptionHandler apiExceptionHandler;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        apiExceptionHandler = new ApiExceptionHandler();
        request = mock(HttpServletRequest.class);
    }

    @Test
    @DisplayName("Test UniqueMailException Handler")
    void testUniqueMailException() {
        RuntimeException ex = new UniqueMailException("Email already exists");

        ResponseEntity<ErrorMessage> response = apiExceptionHandler.UniqueMailException(ex, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email already exists", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Test EntityNotFoundException Handler")
    void testEntityNotFoundException() {
        RuntimeException ex = new EntityNotFoundException("Entity not found");

        ResponseEntity<ErrorMessage> response = apiExceptionHandler.EntityNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity not found", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Test BusinessException Handler")
    void testBusinessException() {
        RuntimeException ex = new BusinessException("Business rule violation");

        ResponseEntity<ErrorMessage> response = apiExceptionHandler.BusinessException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Business rule violation", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Test UnauthorizedException Handler")
    void testUnauthorizedException() {
        RuntimeException ex = new UnauthorizedException("Unauthorized access");

        ResponseEntity<ErrorMessage> response = apiExceptionHandler.UnauthorizedException(ex, request);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized access", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Test MailSendException Handler")
    void testMailSendException() {
        RuntimeException ex = new MailSendException("Error sending email");

        ResponseEntity<ErrorMessage> response = apiExceptionHandler.MailSendException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error sending email", response.getBody().getMessage());
    }
}
