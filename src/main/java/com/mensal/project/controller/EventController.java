package com.mensal.project.controller;

import com.mensal.project.dto.eventdto.EventDto;
import com.mensal.project.dto.eventdto.ResponseEventDto;
import com.mensal.project.dto.eventdto.UpdateEventDto;
import com.mensal.project.mapper.EventMapper;
import com.mensal.project.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    EventMapper mapper;


    @PostMapping("/save")
    public ResponseEntity<ResponseEventDto> save(@Valid @RequestBody EventDto dto){
        var event = mapper.toEntity(dto);
        eventService.save(event);
        return ResponseEntity.ok(mapper.toDto(event));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseEventDto> update(@PathVariable Long id, @RequestBody UpdateEventDto dto){
        var event = mapper.toEntityUpdate(dto);
        event = eventService.update(id, event);
        return ResponseEntity.ok(mapper.toDto(event));
    }
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<ResponseEventDto> findById(@PathVariable Long id){
        var event = eventService.findById(id);
        return new ResponseEntity<>(mapper.toDto(event), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<ResponseEventDto>> findAll(){
        var event = eventService.findAll();
        return new ResponseEntity<>(event.stream().map(mapper::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ResponseEventDto>> findByUserId(@PathVariable Long id){
        var list = eventService.findByUserId(id);
        return new ResponseEntity<>(list.stream().map(mapper::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
