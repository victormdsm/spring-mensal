package com.mensal.project.controller;

import com.mensal.project.entities.Event;
import com.mensal.project.entities.User;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.EventRepository;
import com.mensal.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EventControllerTest {

    @Autowired
    EventController eventController;

    @MockBean
    UserRepository userRepository;

    @MockBean
    EventRepository eventRepository;

    User user = new User(1L, "mamonha@gmail.com", "123456", "+55 45 98416-9058", "mamonha cardoso", UserType.PARTICIPANT, null);



}
