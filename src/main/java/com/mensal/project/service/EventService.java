package com.mensal.project.service;

import com.mensal.project.configuration.exception.BusinessException;
import com.mensal.project.configuration.exception.EntityNotFoundException;
import com.mensal.project.configuration.exception.UnauthorizedException;
import com.mensal.project.entities.Event;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.EventRepository;
import com.mensal.project.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public Event save(Event event) {
        var user = userRepository.findById(event.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        if(!user.getUserType().equals(UserType.ADMIN)){
            throw new UnauthorizedException("Usuario não autorizado a criar um evento");
        }
        if(event.getStartDateTime().isBefore(LocalDateTime.now())) {
            throw new BusinessException("A data de início do evento não pode estar no passado.");
        }
        if(event.getEndDateTime().isBefore(event.getStartDateTime())) {
            throw new BusinessException("A data de término do evento não pode ser anterior à data de início.");
        }
        return this.eventRepository.save(event);
    }

    @Transactional(readOnly = true)
    public Event findById(Long id){
        return eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("evento não encontrado"));
    }

    @Transactional
    public Event update(Long id, Event event){
        Event eventoUpdate = findById(id);

        if (event.getTitle() != null && !event.getTitle().isEmpty()) {
            eventoUpdate.setTitle(event.getTitle());
        }
        if (event.getDescription() != null && !event.getDescription().isEmpty()) {
            eventoUpdate.setDescription(event.getDescription());
        }
        if (event.getCapacity() > 0) {
            eventoUpdate.setCapacity(event.getCapacity());
        }
        if (event.getStartDateTime() != null) {
            eventoUpdate.setStartDateTime(event.getStartDateTime());
        }
        if (event.getEndDateTime() != null) {
            eventoUpdate.setEndDateTime(event.getEndDateTime());
        }
        if (event.getLocation() != null && !event.getLocation().isEmpty()) {
            eventoUpdate.setLocation(event.getLocation());
        }
        if (eventoUpdate.getStartDateTime().isBefore(LocalDateTime.now())) {
            throw new BusinessException("A data de início do evento não pode estar no passado.");
        }
        if (eventoUpdate.getEndDateTime().isBefore(eventoUpdate.getStartDateTime())) {
            throw new BusinessException("A data de término do evento não pode ser anterior à data de início.");
        }
        return eventoUpdate;
    }

    @Transactional(readOnly = true)
    public List<Event> findAll(){
        return this.eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Event> findByUserId(Long id){
        var events = eventRepository.findByUserId(id);
        if (events.isEmpty()) {
            throw new EntityNotFoundException("Nenhum evento encontrado para o usuário com ID " + id);
        }
        return events;
    }

    @Transactional
    public void delete(Long id){eventRepository.deleteById(id);}
}
