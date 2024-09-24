package com.mensal.project.dto;

import com.mensal.project.dto.eventdto.EventDto;
import com.mensal.project.dto.eventdto.EventDtoIntegration;
import com.mensal.project.dto.eventdto.ResponseEventDto;
import com.mensal.project.dto.eventdto.UpdateEventDto;
import com.mensal.project.dto.userdto.UserDtoIntegration;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventDtoTest {

    @Test
     void testEventDto() {
        UserDtoIntegration userDto = new UserDtoIntegration(1L);
        EventDto eventDto = new EventDto("Título do Evento", "Descrição do evento",
                LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                "Localização do Evento", 100, userDto);

        assertEquals("Título do Evento", eventDto.title());
        assertEquals("Descrição do evento", eventDto.description());
        assertNotNull(eventDto.startDateTime());
        assertNotNull(eventDto.endDateTime());
        assertEquals("Localização do Evento", eventDto.location());
        assertEquals(100, eventDto.capacity());
        assertEquals(1L, eventDto.dto().id());
    }

    @Test
    void testEventDtoIntegration() {
        EventDtoIntegration eventDtoIntegration = new EventDtoIntegration(1L);

        assertEquals(1L, eventDtoIntegration.id());
    }

    @Test
    void testResponseEventDto() {
        ResponseEventDto responseEventDto = new ResponseEventDto(1L, "Título",
                "Descrição", LocalDateTime.now(), LocalDateTime.now().plusHours(1),
                "Local", 100);

        assertEquals(1L, responseEventDto.id());
        assertEquals("Título", responseEventDto.title());
        assertEquals("Descrição", responseEventDto.description());
        assertNotNull(responseEventDto.startDateTime());
        assertNotNull(responseEventDto.endDateTime());
        assertEquals("Local", responseEventDto.location());
        assertEquals(100, responseEventDto.capacity());
    }

    @Test
    void testUpdateEventDto() {
        UpdateEventDto updateEventDto = new UpdateEventDto("Novo Título",
                "Nova Descrição", 150, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2), "Nova Localização");

        assertEquals("Novo Título", updateEventDto.title());
        assertEquals("Nova Descrição", updateEventDto.description());
        assertEquals(150, updateEventDto.capacity());
        assertNotNull(updateEventDto.startDateTime());
        assertNotNull(updateEventDto.endDateTime());
        assertEquals("Nova Localização", updateEventDto.location());
    }
}
