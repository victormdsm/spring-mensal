package com.mensal.project.controller;

import com.mensal.project.dto.userdto.ResponseUserDto;
import com.mensal.project.dto.userdto.UpdateUserDto;
import com.mensal.project.dto.userdto.UserDto;
import com.mensal.project.mapper.UserMapper;
import com.mensal.project.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<ResponseUserDto> save(@Valid @RequestBody UserDto dto){
        var user = mapper.toEntity(dto);
        userService.save(user);
        return ResponseEntity.ok(mapper.toDto(user));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<ResponseUserDto> update(@PathVariable Long id, @RequestBody UpdateUserDto dto){
        var user = mapper.toEntityUpdate(dto);
        user = userService.update(id, user);
        return ResponseEntity.ok(mapper.toDto(user));
    }

    @GetMapping("/findbyid/{id}")
    private ResponseEntity<ResponseUserDto> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return new ResponseEntity<>(mapper.toDto(user), HttpStatus.OK);
    }

    @GetMapping("/findall")
    private ResponseEntity<List<ResponseUserDto>> findAll(){
        var user = userService.findAll();
        return new ResponseEntity<>(user.stream().map(mapper::toDto).collect(Collectors.toList()), HttpStatus.OK);
    }
}
