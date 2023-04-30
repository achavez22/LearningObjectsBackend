package com.exam;


import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(ExamserverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
		 try{
			User user = new User();

            user.setFirstName("adan");
            user.setLastName("chavez");
            user.setUsername("achavez");
            user.setPassword(this.bCryptPasswordEncoder.encode("12345"));
            user.setEmail("achavez@gmail.com");
            user.setPhoneo("988212020");
            user.setProfile("perfil.png");

			Role rol = new Role();
			rol.setRolId(2L);
			rol.setRolName("NORMAL");

			Set<UserRole> usuariosRoles = new HashSet<>();
			UserRole usuarioRol = new UserRole();
			usuarioRol.setRole(rol);
			usuarioRol.setUser(user);
			usuariosRoles.add(usuarioRol);

			User usuarioGuardado = usuarioService.saveUser(user,usuariosRoles);
			System.out.println(usuarioGuardado.getUsername());
		}catch (Exception e){
			e.printStackTrace();
		}
        */

    }

}
