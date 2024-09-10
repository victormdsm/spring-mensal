package com.mensal.project.service;

import ch.qos.logback.core.joran.event.EndEvent;
import com.mensal.project.entities.Event;
import com.mensal.project.entities.User;
import com.mensal.project.repository.EventRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public Event save(Event event) {
        return this.eventRepository.save(event);
    }

    @Transactional(readOnly = true)
    public Event findById(Long id){
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("evento não encontrado"));
    }

    @Transactional
    public Event update(Long id, Event event){
        Event update = findById(id);
        System.out.println(event.getId());
        System.out.println(update.toString());
        return null;
    }

    @Transactional(readOnly = true)
    public List<Event> findAll(){
        return this.eventRepository.findAll();
    }
}
