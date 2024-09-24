package com.mensal.project.controller;

import com.mensal.project.configuration.exception.EntityNotFoundException;
import com.mensal.project.dto.eventdto.EventDtoIntegration;
import com.mensal.project.dto.userdto.UserDtoIntegration;
import com.mensal.project.dto.usereventdto.ResponseUserEventDto;
import com.mensal.project.dto.usereventdto.UserEventDto;
import com.mensal.project.entities.Event;
import com.mensal.project.entities.User;
import com.mensal.project.entities.UserEvent;
import com.mensal.project.entities.enums.Status;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.EventRepository;
import com.mensal.project.repository.UserEventRepository;
import com.mensal.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserEventControllerTest {

    @Autowired
    UserEventController userEventController;

    @MockBean
    UserEventRepository userEventRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    EventRepository eventRepository;

    User userAdmin = new User(1L, "admin@gmail.com", "123456", "+55 45 98416-9058", "Admin", UserType.ADMIN, null);
    User userParticipant = new User(2L, "participant@gmail.com", "123456", "+55 45 98416-9058", "Participant", UserType.PARTICIPANT, null);
    Event event = new Event(1L, "Evento Teste", "Descrição do Evento", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), "Local Teste", 100, userAdmin, null, null);
    UserEvent userEvent = new UserEvent(1L, LocalDateTime.now(), Status.PENDING, userParticipant, event);

    @BeforeEach
    void setup() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userAdmin));
        when(userRepository.findById(2L)).thenReturn(Optional.of(userParticipant));
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(userEventRepository.save(any(UserEvent.class))).thenReturn(userEvent);
        when(userEventRepository.findById(1L)).thenReturn(Optional.of(userEvent));
    }

    @Test
    @DisplayName("Testando salvar UserEvent")
    void cenario01() {
        var userEventDto = new UserEventDto(new UserDtoIntegration(2L), new EventDtoIntegration(1L));

        // Simular a entidade UserEvent a ser salva
        UserEvent mockUserEvent = new UserEvent();
        mockUserEvent.setId(1L);
        mockUserEvent.setStatus(Status.PENDING);

        // Quando o repositório salvar a entidade, retorna a mockada
        when(userEventService.save(any(UserEventDto.class))).thenReturn(mockUserEvent);

        ResponseEntity<ResponseUserEventDto> response = userEventController.save(userEventDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().id());
        assertEquals(Status.PENDING, response.getBody().participantStatus());
    }

    @Test
    @DisplayName("Testando erro ao salvar UserEvent com evento não encontrado")
    void cenario02() {
        var userEventDto = new UserEventDto(new UserDtoIntegration(2L), new EventDtoIntegration(2L));
        doThrow(new EntityNotFoundException("Event not found")).when(userEventRepository).save(userEventDto);

        assertThrows(EntityNotFoundException.class, () -> userEventController.save(userEventDto));
    }

    @Test
    @DisplayName("Testando erro ao salvar UserEvent com usuário não encontrado")
    void cenario03() {
        var userEventDto = new UserEventDto(new UserDtoIntegration(3L), new EventDtoIntegration(1L));
        doThrow(new EntityNotFoundException("User not found")).when(userEventService).save(userEventDto);

        assertThrows(EntityNotFoundException.class, () -> userEventController.save(userEventDto));
    }

    @Test
    @DisplayName("Testando encontrar UserEvent por ID")
    void cenario04() {
        var response = userEventController.findById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Testando erro ao buscar UserEvent não encontrado")
    void cenario05() {
        when(userEventRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userEventController.findById(2L));
    }

}
