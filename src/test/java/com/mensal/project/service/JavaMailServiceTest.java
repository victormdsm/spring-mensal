package com.mensal.project.service;

import com.mensal.project.entities.Event;
import com.mensal.project.entities.Invitation;
import com.mensal.project.entities.User;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.EventRepository;
import com.mensal.project.repository.InvitationRepository;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JavaMailServiceTest {

    @Autowired
    JavaMailService javaMailService;

    @MockBean
    InvitationRepository invitationRepository;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    JavaMailSender javaMailSender;

    Invitation invitation;
    Event event;
    User user;

    @BeforeEach
    void setup() {
        user = new User(1L, "mamonha@gmail.com", "123456", "+55 45 98416-9058", "mamonha cardoso", UserType.ADMIN, null);
        event = new Event(1L, "Churrascao do mamonha", "Churrasco para comemorar", LocalDateTime.of(2024, 9, 27, 10, 0),
                LocalDateTime.of(2024, 9, 28, 12, 0), "Local do Evento", 100, user, null, null);
        invitation = new Invitation(1L, "convidado@teste.com", LocalDateTime.now(), event);

        Mockito.when(eventRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(event));
        Mockito.when(invitationRepository.save(ArgumentMatchers.any(Invitation.class))).thenReturn(invitation);
    }

    @Test
    @DisplayName("Testar envio de e-mail e salvar convite")
    void testSendEmail() {

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        javaMailService.sendEmail(invitation);

        Mockito.verify(eventRepository, Mockito.times(1)).findById(event.getId());

        Mockito.verify(javaMailSender, Mockito.times(1)).send(mimeMessage);

        Mockito.verify(invitationRepository, Mockito.times(1)).save(ArgumentMatchers.any(Invitation.class));
    }
}
