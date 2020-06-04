package org.polytech.course;

import org.polytech.course.entity.User;
import org.polytech.course.repository.BookRepository;
import org.polytech.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RolesInit implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("user", passwordEncoder.encode("default"), Collections.singletonList("ROLE_USER")));
        userRepository.save(new User("admin", passwordEncoder.encode("root"), Collections.singletonList("ROLE_ADMIN")));
    }
}