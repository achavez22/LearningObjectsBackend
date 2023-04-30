package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/")
    public User saveUser(@RequestBody User user) throws Exception{
        user.setProfile("default.png");
        //encoding password with BCryptPasswordEncoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> userRoles = new HashSet<>();

        Role rol = new Role();
        rol.setRolId(2L);
        rol.setRolName("NORMAL");

        UserRole userRol = new UserRole();
        userRol.setUser(user);
        userRol.setRole(rol);

        userRoles.add(userRol);
        return usuarioService.saveUser(user,userRoles);
    }

    @GetMapping("/test")
    public String test(){
        return "Wlcome to backend api of ExamPortal";
    }

    @GetMapping("/{username}")
    public User getUsuario(@PathVariable("username") String username){
        return usuarioService.getUser(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.deleteUser(usuarioId);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){
        return ResponseEntity.ok(ex.getMessage());
    }

}
