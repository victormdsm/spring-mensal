package com.mensal.project.service;

import com.mensal.project.configuration.exception.EntityNotFoundException;
import com.mensal.project.configuration.exception.UniqueMailException;
import com.mensal.project.entities.User;
import com.mensal.project.entities.enums.Status;
import com.mensal.project.entities.enums.UserType;
import com.mensal.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User save(User user) {
        try{
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex){
            throw new UniqueMailException("Este endereço de email já esta sendo utilizado");
        }

    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User update(Long id, User user){

        User update = findById(id);
        if(user.getPhone() != null && !user.getPhone().equals(update.getPhone())){
            update.setPhone(user.getPhone());
        }
        if (user.getEmail() != null && !user.getEmail().equals(update.getEmail())){
            update.setEmail(user.getEmail());
        }
        if (user.getName() != null && !user.getName().equals(update.getName())){
            update.setName(user.getName());
        }
        return save(update);
    }

    public User setRole(Long id, UserType status) {
        var user = findById(id);
        user.setUserType(status);
        return save(user);
    }

}
