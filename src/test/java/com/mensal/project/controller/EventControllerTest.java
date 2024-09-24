package com.mensal.project.controller;

import com.mensal.project.configuration.exception.BusinessException;
import com.mensal.project.configuration.exception.EntityNotFoundException;
import com.mensal.project.configuration.exception.UnauthorizedException;
import com.mensal.project.dto.eventdto.EventDto;
import com.mensal.project.dto.eventdto.ResponseEventDto;
import com.mensal.project.dto.eventdto.UpdateEventDto;
import com.mensal.project.dto.userdto.UserDtoIntegration;
import com.mensal.project.entities.Event;
import com.mensal.project.entities.User;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.EventRepository;
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
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventControllerTest {

    @Autowired
    EventController eventController;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    UserRepository userRepository;

    User userAdmin = new User(1L, "admin@gmail.com", "123456", "+55 45 98416-9058", "Admin", UserType.ADMIN, null);
    User userParticipant = new User(2L, "participant@gmail.com", "123456", "+55 45 98416-9058", "Participant", UserType.PARTICIPANT, null);
    Event event = new Event(1L, "Evento Teste", "Descrição do Evento", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), "Local Teste", 100, userAdmin, null, null);

    @BeforeEach
    void setup() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userAdmin));
        when(userRepository.findById(2L)).thenReturn(Optional.of(userParticipant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
    }

    @Test
    @DisplayName("Testando salvar evento")
    void cenario01() {
        var eventDto = new EventDto("Evento Teste", "Descrição do Evento", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), "Local Teste", 100, new UserDtoIntegration(1L));
        ResponseEntity<ResponseEventDto> response = eventController.save(eventDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Evento Teste", response.getBody().title());
    }

    @Test
    @DisplayName("Testando erro ao salvar evento com usuário não autorizado")
    void cenario02() {
        var eventDto = new EventDto("Evento Teste", "Descrição do Evento", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), "Local Teste", 100, new UserDtoIntegration(2L));
        assertThrows(UnauthorizedException.class, () -> eventController.save(eventDto));
    }

    @Test
    @DisplayName("Testando erro ao salvar evento com data de início no passado")
    void cenario03() {
        var eventDto = new EventDto("Evento Teste", "Descrição do Evento", LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2), "Local Teste", 100, new UserDtoIntegration(1L));
        assertThrows(BusinessException.class, () -> eventController.save(eventDto));
    }

    @Test
    @DisplayName("Testando erro ao salvar evento com data de término antes da data de início")
    void cenario04() {
        var eventDto = new EventDto("Evento Teste", "Descrição do Evento", LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(1), "Local Teste", 100, new UserDtoIntegration(1L));
        assertThrows(BusinessException.class, () -> eventController.save(eventDto));
    }

    @Test
    @DisplayName("Testando find by id")
    void cenario05() {
        var response = eventController.findById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Evento Teste", response.getBody().title());
    }

    @Test
    @DisplayName("Testando erro ao buscar evento não encontrado")
    void cenario06() {
        when(eventRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> eventController.findById(2L));
    }

    @Test
    @DisplayName("Testando find all")
    void cenario07() {
        var lista = Arrays.asList(
                new Event(1L, "Evento 1", "Descrição 1", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), "Local 1", 100, userAdmin, null, null),
                new Event(2L, "Evento 2", "Descrição 2", LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4), "Local 2", 150, userAdmin, null, null)
        );
        when(eventRepository.findAll()).thenReturn(lista);

        var response = eventController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("Testando atualizar evento")
    void cenario08() {
        var updateDto = new UpdateEventDto("Evento Atualizado", "Descrição Atualizada", 150 ,LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4), "Local Atualizado");
        var response = eventController.update(1L, updateDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Evento Atualizado", response.getBody().title());
    }

    @Test
    @DisplayName("Testando erro ao atualizar evento não encontrado")
    void cenario09() {
        when(eventRepository.findById(2L)).thenReturn(Optional.empty());
        var updateDto = new UpdateEventDto("Evento Inexistente", "Descrição Inexistente", 150 ,LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), "Local Inexistente");
        assertThrows(EntityNotFoundException.class, () -> eventController.update(2L, updateDto));
    }

    @Test
    @DisplayName("Testando deletar evento")
    void cenario10() {
        var response = eventController.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
