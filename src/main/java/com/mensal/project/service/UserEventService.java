package com.mensal.project.service;

import com.mensal.project.configuration.exception.EntityNotFoundException;
import com.mensal.project.dto.ReportDto;
import com.mensal.project.entities.Event;
import com.mensal.project.entities.User;
import com.mensal.project.entities.UserEvent;
import com.mensal.project.repository.EventRepository;
import com.mensal.project.repository.UserEventRepository;
import com.mensal.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserEventService {

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public UserEvent save(UserEvent user) {
        var usuario = userRepository.findById(user.getUsers().getId()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        var evento = eventRepository.findById(user.getEvent().getId()).orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));

        return userEventRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserEvent findById(Long id){
        return userEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("evento não encontrado"));
    }

    @Transactional
    public UserEvent update(Long id, UserEvent userEvent){
        UserEvent update = findById(id);
        update.setParticipantStatus(userEvent.getParticipantStatus());
        return update;
    }

    @Transactional(readOnly = true)
    public List<UserEvent> findAll(){
        return this.userEventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ReportDto reports(Long id) {
        var event = findById(id);
        var total = userEventRepository.countById(id);
        var confirmed = userEventRepository.countByEventIdAndStatus(id, "CONFIRMED");
        var pending = userEventRepository.countByEventIdAndStatus(id, "PENDING");
        var cancelled = userEventRepository.countByEventIdAndStatus(id, "CANCELLED");

        return new ReportDto(total, confirmed, pending, cancelled);
    }
}
