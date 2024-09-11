package com.mensal.project.controller;


import com.mensal.project.dto.eventdto.ResponseEventDto;
import com.mensal.project.dto.eventdto.UpdateEventDto;
import com.mensal.project.dto.usereventdto.ResponseUserEventDto;
import com.mensal.project.dto.usereventdto.UpdateUserEventDto;
import com.mensal.project.dto.usereventdto.UserEventDto;
import com.mensal.project.mapper.UserEventMapper;
import com.mensal.project.service.UserEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-events")
public class UserEventController {

    @Autowired
    private UserEventService userEventService;

    @Autowired
    UserEventMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<ResponseUserEventDto> save(@Valid @RequestBody UserEventDto dto){
        var userEvent = mapper.toEntity(dto);
        userEventService.save(userEvent);
        return ResponseEntity.ok(mapper.toDto(userEvent));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<ResponseUserEventDto> update(@PathVariable Long id, @RequestBody UpdateUserEventDto dto){
        var event = mapper.toEntityUpdate(dto);
        event = userEventService.update(id, event);
        return ResponseEntity.ok(mapper.toDto(event));
    }
    @GetMapping("/findbyid/{id}")
    private ResponseEntity<ResponseUserEventDto> findById(@PathVariable Long id){
        var event = userEventService.findById(id);
        return new ResponseEntity<>(mapper.toDto(event), HttpStatus.OK);
    }

    @GetMapping("/findall")
    private ResponseEntity<List<ResponseUserEventDto>> findAll(){
        var event = userEventService.findAll();
        return new ResponseEntity<>(event.stream().map(mapper::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }
}
