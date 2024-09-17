package com.mensal.project.service;

import com.mensal.project.entities.Invitation;
import com.mensal.project.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class JavaMailService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Invitation invitation) {

        String htmlContent = "<!DOCTYPE html>" +
                "<html lang=\"pt-BR\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Convite para Evento</title>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 80%; margin: auto; padding: 20px; background: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }" +
                ".header { text-align: center; padding: 10px 0; background-color: #007BFF; color: #ffffff; border-radius: 8px 8px 0 0; }" +
                ".header h1 { margin: 0; font-size: 24px; }" +
                ".content { padding: 20px; }" +
                ".content p { font-size: 16px; line-height: 1.6; }" +
                ".footer { text-align: center; padding: 10px; background-color: #f4f4f4; border-radius: 0 0 8px 8px; }" +
                ".footer p { margin: 0; font-size: 14px; color: #777777; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<div class=\"header\">" +
                "<h1>Convite para o Evento</h1>" +
                "</div>" +
                "<div class=\"content\">" +
                "<p>Olá,</p>" +
                "<p>Você está convidado para o evento <strong>" + invitation.getEvent().getTitle() + "</strong>!</p>" +
                "<p>O evento acontecerá no dia <strong>" + invitation.getEvent().getStartDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</strong> às <strong>" + invitation.getEvent().getStartDateTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "</strong>, no local: <strong>" + invitation.getEvent().getLocation() + "</strong>.</p>" +
                "<p>Esperamos contar com sua presença!</p>" +
                "<p>Atenciosamente,<br>A equipe do Evento</p>" +
                "</div>" +
                "<div class=\"footer\">" +
                "<p>Se você não deseja mais receber nossos e-mails, por favor, entre em contato com nossa equipe.</p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(invitation.getEmail());
        String subject = "Convite para o evento: " + invitation.getEvent().getTitle();
        message.setSubject(subject);
        message.setText(htmlContent);
        message.setFrom("tc84214436@mail.com");
        mailSender.send(message);
        invitationRepository.save(invitation);

    }
}
