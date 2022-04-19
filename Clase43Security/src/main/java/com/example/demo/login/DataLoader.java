package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.example.demo.login.AppUsuarioRoles.ADMIN;

//nos carga datos en la base
@Component
public class DataLoader implements ApplicationRunner {

    //para insertar los datps necesitamos el repo
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //password encoder para encriptarlo
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password2");

        userRepository.save(new AppUser("Sarah","Sarah","Sarah@hotmail.com",password, ADMIN));
        userRepository.save(new AppUser("Sarah2","Sarah2","Sara2h@hotmail.com",password2,AppUsuarioRoles.USER ));

    }
}
