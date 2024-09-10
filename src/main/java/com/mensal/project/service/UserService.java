package com.mensal.project.service;

import com.mensal.project.entities.User;
import com.mensal.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
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
        System.out.println(user.getId());
        System.out.println(update.toString());
        return update;
    }
}
