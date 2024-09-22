package com.mensal.project.service;

import com.mensal.project.entities.Event;
import com.mensal.project.entities.User;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.EventRepository;
import com.mensal.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    EventService eventService;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    UserRepository userRepository;

    User user = new User(1L, "mamonha@gmail.com", "123456", "+55 45 98416-9058", "mamonha cardoso", UserType.ADMIN, null);

    Event event = new Event(1L, "Churrascao do mamonha", "Churrasco para comemorar a existencia do bigchungus",
            LocalDateTime.of(2024, 9, 27, 10, 0), LocalDateTime.of(2024, 9, 28, 12, 0),
            "Local do Evento", 100, user, null, null);

    Event updatedEvent = new Event(1L, "Churrascão atualizado", "Evento atualizado",
            LocalDateTime.of(2024, 10, 27, 15, 0), LocalDateTime.of(2024, 10, 28, 17, 0),
            "Novo Local", 150, user, null, null);

    @BeforeEach
    void setup(){
        user.setUserType(UserType.ADMIN);

        Mockito.when(eventRepository.save(any(Event.class))).thenReturn(event);
        Mockito.when(eventRepository.findById(any(Long.class))).thenReturn(Optional.of(event));

        Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
    }

    @Test
    @DisplayName("TESTE DE SALVAR EVENTO")
    void cenario01() {
        var teste = eventService.save(event);
        Assertions.assertEquals(1L, teste.getId());
        Assertions.assertEquals("Churrascao do mamonha", teste.getTitle());
    }

    @Test
    @DisplayName("TESTE DE ATUALIZAR EVENTO")
    void cenario02() {
        Mockito.when(eventRepository.save(any(Event.class))).thenReturn(updatedEvent);

        var teste = eventService.update(1L, updatedEvent);
        Assertions.assertEquals(1L, teste.getId());
        Assertions.assertEquals("Churrascão atualizado", teste.getTitle());
        Assertions.assertEquals("Novo Local", teste.getLocation());
    }

    @Test
    @DisplayName("Find By Id")
    void cenario03() {
        var teste = eventService.findById(1L);
        Assertions.assertEquals(1L, teste.getId());
        Assertions.assertEquals("Churrascao do mamonha", teste.getTitle());
    }

    @Test
    @DisplayName("findAll")
    void cenario04() {
        var events = Arrays.asList(
                new Event(1L, "Evento 1", "Descrição Evento 1", LocalDateTime.of(2024, 9, 21, 10, 0), LocalDateTime.of(2024, 9, 21, 12, 0), "Local 1", 100, user, null, null),
                new Event(2L, "Evento 2", "Descrição Evento 2", LocalDateTime.of(2024, 9, 22, 11, 0), LocalDateTime.of(2024, 9, 22, 13, 0), "Local 2", 200, user, null, null),
                new Event(3L, "Evento 3", "Descrição Evento 3", LocalDateTime.of(2024, 9, 23, 12, 0), LocalDateTime.of(2024, 9, 23, 14, 0), "Local 3", 300, user, null, null)
        );

        Mockito.when(eventRepository.findAll()).thenReturn(events);

        var resultado = eventService.findAll();
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(3, resultado.size());
        Assertions.assertEquals("Evento 1", resultado.get(0).getTitle());
    }

}
