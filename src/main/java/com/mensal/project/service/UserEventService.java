package com.mensal.project.service;

import com.mensal.project.entities.Event;
import com.mensal.project.entities.User;
import com.mensal.project.entities.UserEvent;
import com.mensal.project.repository.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserEventService {

    @Autowired
    private UserEventRepository userEventRepository;

    @Transactional
    public UserEvent save(UserEvent user) {
        return userEventRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserEvent findById(Long id){
        return userEventRepository.findById(id).orElseThrow(() -> new RuntimeException("evento não encontrado"));
    }

    @Transactional
    public UserEvent update(Long id, UserEvent userEvent){
        UserEvent update = findById(id);
        System.out.println(userEvent.getId());
        System.out.println(update.toString());
        return null;
    }

    @Transactional(readOnly = true)
    public List<UserEvent> findAll(){
        return this.userEventRepository.findAll();
    }
}
