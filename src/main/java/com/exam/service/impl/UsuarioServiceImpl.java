package com.exam.service.impl;


import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RolRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public User saveUser(User user, Set<UserRole> usuarioRoles) throws Exception {
        User usserLocal = userRepository.findByUsername(user.getUsername());
        if(usserLocal != null){
            System.out.println("user is already there");
            throw new UserFoundException();
        }
        else{
            for(UserRole usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRole());
            }
            user.getUserRoles().addAll(usuarioRoles);
            usserLocal = userRepository.save(user);
        }
        return usserLocal;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long usuarioId) {
        userRepository.deleteById(usuarioId);
    }

}