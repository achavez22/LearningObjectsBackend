package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UsuarioService {

    public User saveUser(User usuario, Set<UserRole> usuarioRoles) throws Exception;

    public User getUser(String username);

    public void deleteUser(Long usuarioId);
}
