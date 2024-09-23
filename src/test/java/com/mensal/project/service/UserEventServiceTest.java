package com.mensal.project.service;

import com.mensal.project.entities.Event;
import com.mensal.project.entities.User;
import com.mensal.project.entities.UserEvent;
import com.mensal.project.entities.enums.Status;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.EventRepository;
import com.mensal.project.repository.UserEventRepository;
import com.mensal.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserEventServiceTest {

    @Autowired
    UserEventService userEventService;

    @MockBean
    UserEventRepository userEventRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    EventRepository eventRepository;

    User user = new User(1L, "mamonha@gmail.com", "123456", "+55 45 98416-9058", "mamonha cardoso", UserType.ADMIN, null);
    Event event = new Event(1L, "Churrascao do mamonha", "Churrasco para comemorar a existencia do bigchungus",
            LocalDateTime.of(2024, 9, 27, 10, 0), LocalDateTime.of(2024, 9, 28, 12, 0),
            "Churrascaria Big Chungus", 100, user, null, null);

    UserEvent userEvent = new UserEvent(1L, LocalDateTime.now(), Status.PENDING ,user, event);
    UserEvent userEventUpdated = new UserEvent(1L, LocalDateTime.now(), Status.CONFIRMED ,user, event);

    @BeforeEach
    void Setup(){
        user.setUserType(UserType.ADMIN);
        Mockito.when(eventRepository.findById(any(Long.class))).thenReturn(Optional.of(event));
        Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        Mockito.when(userEventRepository.save(any(UserEvent.class))).thenReturn(userEvent);
        Mockito.when(userEventRepository.findById(any(Long.class))).thenReturn(Optional.of(userEvent));
    }

    @Test
    @DisplayName("Salvando entidade")
    void Cenario01(){
        var obj = userEventService.save(userEvent);
        assertEquals(1L, obj.getId());
        assertEquals("mamonha cardoso", userEvent.getUsers().getName());
    }

    @Test
    @DisplayName("Atualizando Status")
    void Cenario02() {
        var obj =  userEventService.update( 1L, userEventUpdated);
        assertEquals(Status.CONFIRMED, obj.getParticipantStatus());
        assertEquals(user, obj.getUsers());
    }

    @Test
    @DisplayName("find by id")
    void Cenario03() {
        var obj = userEventService.findById(1L);
        assertEquals(1L, obj.getId());
        assertEquals("mamonha cardoso", userEvent.getUsers().getName());
    }

    @Test
    @DisplayName("Find all")
    void Cenario04() {

        var usersEvents = Arrays.asList(
                new UserEvent(1L, LocalDateTime.now(), Status.CONFIRMED, user, event),
                new UserEvent(2L, LocalDateTime.now(), Status.PENDING, user, event)
        );

        Mockito.when(userEventRepository.findAll()).thenReturn(usersEvents);

        var allEvents = userEventRepository.findAll();
        Assertions.assertEquals(2, allEvents.size());
        Assertions.assertNotNull(allEvents);

    }

}
