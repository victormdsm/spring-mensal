package com.mensal.project.controller;

import com.mensal.project.configuration.exception.EntityNotFoundException;
import com.mensal.project.configuration.exception.UniqueMailException;
import com.mensal.project.dto.userdto.ResponseUserDto;
import com.mensal.project.dto.userdto.UpdateUserDto;
import com.mensal.project.dto.userdto.UserDto;
import com.mensal.project.entities.User;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.sql.Array;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {


    @Autowired
    UserController userController;


    @MockBean
    UserRepository userRepository;

    User user = new User(1L, "mamonha@gmail.com", "123456", "+55 45 98416-9058", "mamonha cardoso", UserType.PARTICIPANT, null);

    @BeforeEach
    void Setup() {

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    @DisplayName("Testando save")
    void cenario01() {
        var obj = new UserDto("mamonha@gmail.com", "123456", "+55 45 98416-9058", "mamonha cardoso");
        ResponseEntity<ResponseUserDto> objResponse = userController.save(obj);
        assertEquals(HttpStatus.CREATED, objResponse.getStatusCode());
        assertEquals("mamonha cardoso", objResponse.getBody().name());
    }

//    @Test
//    @DisplayName("Testando Erro de validacao de campo")
//    void cenario02() {
//      var obj = new UserDto("", "", "", "");
//      assertThrows(MethodArgumentNotValidException.class, () ->
//      userController.save(obj));
//  }

    @Test
    @DisplayName("Testando find by id")
    void Cenario03(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        var obj = userController.findById(1L);
        assertEquals(HttpStatus.OK, obj.getStatusCode());
        assertEquals("mamonha cardoso",  obj.getBody().name());
    }

    @Test
    @DisplayName("Testando find by id")
    void Cenario04(){
        assertThrows(EntityNotFoundException.class, () -> {
            userController.findById(2L);
        } );
    }

    @Test
    @DisplayName("Find All")
    void Cenario05() {
        var lista = Arrays.asList(
                new User(1L, "mamonha1@gmail.com", "1234567", "+55 45 98416-9058", "mamonha cardoso zeni", UserType.PARTICIPANT, null),
                new User(2L, "mamonha2@gmail.com", "1234568", "+55 45 98416-9058", "mamonha cardoso da silva", UserType.PARTICIPANT, null),
                new User(3L, "mamonha3@gmail.com", "1234569", "+55 45 98416-9058", "mamonha cardoso ubeda", UserType.PARTICIPANT, null),
                new User(4L, "mamonha4@gmail.com", "12345610", "+55 45 98416-9058", "mamonha cardoso bogler", UserType.PARTICIPANT, null)
        );
        when(userRepository.findAll()).thenReturn(lista);

        var users = userController.findAll();
        assertEquals(4, users.getBody().size());
        assertEquals(HttpStatus.OK, users.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar")
    void Cenario06() {
        var dto = new UpdateUserDto("mamonha cardoso bogler", "+55 45 98426-9058", "mamonha302@gmail.com");
        var updated = userController.update(1L,  dto);
        assertEquals(HttpStatus.OK, updated.getStatusCode());
        assertEquals(dto.name(), updated.getBody().name());
    }

    @Test
    @DisplayName("Erro de email duplicado")
    void Cenario07() {

        when(userRepository.save(any(User.class))).thenThrow(new UniqueMailException("email inválido"));
        var obj = new UserDto("mamonha@gmail.com", "123456", "+55 45 98416-9058", "mamonha cardoso");
        assertThrows(UniqueMailException.class, () -> userController.save(obj));

    }
}
