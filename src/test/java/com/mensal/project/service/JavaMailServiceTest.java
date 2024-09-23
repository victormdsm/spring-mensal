package com.mensal.project.service;

import com.mensal.project.configuration.exception.EntityNotFoundException;
import com.mensal.project.configuration.exception.MailSendException;
import com.mensal.project.entities.Event;
import com.mensal.project.entities.Invitation;
import com.mensal.project.entities.User;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.EventRepository;
import com.mensal.project.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

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
    Event event = new Event(1L, "Churrascao do mamonha", "Churrasco para comemorar a existencia do bigchungus",
            LocalDateTime.of(2024, 9, 27, 10, 0), LocalDateTime.of(2024, 9, 28, 12, 0),
            "Churrascaria Big Chungus", 100, null, null, null);

    @BeforeEach
    void setup() {

        invitation = new Invitation(1L, "guest@example.com", LocalDateTime.now(),event);

        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    @DisplayName("Enviar e-mail com sucesso")
    void sendEmailSuccess() {
        assertDoesNotThrow(() -> javaMailService.sendEmail(invitation));
    }


    @Test
    @DisplayName("Falha ao enviar e-mail")
    void sendEmailFailure() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Mockito.doThrow(new RuntimeException("Falha ao enviar e-mail")).when(javaMailSender).send(mimeMessage);

        MailSendException exception = assertThrows(MailSendException.class, () -> javaMailService.sendEmail(invitation));
        assertEquals("Falha ao enviar convite", exception.getMessage());
    }

    @Test
    @DisplayName("Salvar convite após enviar o e-mail")
    void saveInvitationAfterSendingEmail() {
        javaMailService.sendEmail(invitation);
        Mockito.verify(invitationRepository, Mockito.times(1)).save(invitation);
    }
}
