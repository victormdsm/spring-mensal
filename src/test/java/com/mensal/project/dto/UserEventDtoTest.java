package com.mensal.project.dto;

import com.mensal.project.dto.eventdto.EventDto;
import com.mensal.project.dto.eventdto.EventDtoIntegration;
import com.mensal.project.dto.userdto.UserDto;
import com.mensal.project.dto.userdto.UserDtoIntegration;
import com.mensal.project.dto.usereventdto.ResponseUserEventDto;
import com.mensal.project.dto.usereventdto.UpdateUserEventDto;
import com.mensal.project.dto.usereventdto.UserEventDto;
import com.mensal.project.entities.enums.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserEventDtoTest {

    @Test
    void testUserEventDto() {
        UserDtoIntegration userDtoIntegration = new UserDtoIntegration(1L);
        EventDtoIntegration eventDtoIntegration = new EventDtoIntegration(1L);
        UserEventDto userEventDto = new UserEventDto(userDtoIntegration, eventDtoIntegration);

        assertEquals(1L, userEventDto.user().id());
        assertEquals(1L, userEventDto.event().id());
    }

    @Test
    void testResponseUserEventDto() {
        UserDto userDto = new UserDto("user@example.com", "password", "+55 12 91234-5678", "User Name");
        EventDto eventDto = new EventDto("Título do Evento", "Descrição do evento",
                LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                "Localização do Evento", 100, new UserDtoIntegration(1L));
        ResponseUserEventDto responseUserEventDto = new ResponseUserEventDto(1L, LocalDateTime.now(),
                Status.CONFIRMED, userDto, eventDto);

        assertEquals(1L, responseUserEventDto.id());
        assertNotNull(responseUserEventDto.registrationDateTime());
        assertEquals( Status.CONFIRMED, responseUserEventDto.participantStatus());
        assertEquals("user@example.com", responseUserEventDto.user().email());
        assertEquals("Título do Evento", responseUserEventDto.event().title());
    }

    @Test
    void testUpdateUserEventDto() {
        UpdateUserEventDto updateUserEventDto = new UpdateUserEventDto( Status.CONFIRMED);

        assertEquals( Status.CONFIRMED, updateUserEventDto.participantStatus());
    }
}
