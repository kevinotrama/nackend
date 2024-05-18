package com.velialiyev.twitterclone.config;

import com.velialiyev.twitterclone.entity.UserEntity;
import com.velialiyev.twitterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminInitializationService implements ApplicationRunner {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (usuarioRepository.findByUsername("administrador").isPresent() == false) {
            UserEntity admin = UserEntity.builder()
                    .firstName("administrador")
                    .lastName("administrador")
                    .bio("administrador")
                    .email("administrador@admin.a")
                    .rol("administrador")
                    .profilePicturePath("./imagenes/DummyProfilePicture.jpg")
                    .bannerPicturePath("./imagenes/DummyBannerPicture.jpg")
                    .build();
            admin.setUsername("administrador");
            admin.setPassword(passwordEncoder.encode("admintdea"));
            usuarioRepository.save(admin);
        }
    }
}
