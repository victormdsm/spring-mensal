package com.mensal.project.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mensal.project.entities.User;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    User user = new User(1L, "mamonha@gmail.com", "123456", "+55 45 98416-9058", "mamonha cardoso", UserType.PARTICIPANT, null);
    User userUpdate = new User(1L, "mamonha302@gmail.com", "1234567", "+55 45 98426-9058", "mamonha cardoso da silva", UserType.PARTICIPANT, null);

    @BeforeEach
    void setup(){
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
    }

    @Test
    @DisplayName("TESTE DE SALVAR USUARIO")
    void cenario01() {
        var teste = userService.save(user);
        Assertions.assertEquals(1L, teste.getId());
        Assertions.assertEquals("mamonha@gmail.com", teste.getEmail());
    }

    @Test
    @DisplayName("TESTE DE ATUALIZAR USUARIO")
    void cenario02() {

        var teste = userService.update(1L, userUpdate);
        Assertions.assertEquals(1L, teste.getId());
        Assertions.assertEquals("mamonha cardoso da silva", teste.getName());
    }

    @Test
    @DisplayName("Find By Id")
    void cenario03() {
        var teste = userService.findById(1l);
        Assertions.assertEquals(1l, teste.getId());
    }

    @Test
    @DisplayName("findAll")
    void cenario04() {
        var users = Arrays.asList(
                new User(1L, "mamonha1@gmail.com", "123456", "+55 45 98416-9158", "mamonha cardoso zeni", UserType.PARTICIPANT, null),
                new User(2L, "mamonha2@gmail.com", "1234567", "+55 45 98416-9258", "mamonha cardoso ubeda", UserType.PARTICIPANT, null),
                new User(4L, "mamonha3@gmail.com", "1234568", "+55 45 98416-9358", "mamonha cardoso da silva", UserType.PARTICIPANT, null)
        );

        Mockito.when(userRepository.findAll()).thenReturn(users);

        var resultado = userService.findAll();
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(3, resultado.size());
        Assertions.assertEquals("mamonha cardoso zeni", resultado.get(0).getName());
    }

}
