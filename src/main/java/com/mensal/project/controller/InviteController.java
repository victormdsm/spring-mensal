package com.mensal.project.controller;

import com.mensal.project.dto.InviteDto;
import com.mensal.project.entities.Invitation;
import com.mensal.project.mapper.InviteMapper;
import com.mensal.project.service.JavaMailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invite")
public class InviteController {

    @Autowired
    private JavaMailService javaMailService;

    @Autowired
    private InviteMapper inviteMapper;

    @PostMapping("/send")
    public ResponseEntity<Void> sendInvite(@Valid @RequestBody InviteDto dto){
        var invite = inviteMapper.toEntity(dto);
        javaMailService.sendEmail(invite);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
