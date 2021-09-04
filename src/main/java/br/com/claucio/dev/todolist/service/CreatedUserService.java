package br.com.claucio.dev.todolist.service;

import br.com.claucio.dev.todolist.domain.AppUser;
import br.com.claucio.dev.todolist.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatedUserService {

    private final AppUserRepository appUserRepository;

    public AppUser createdUser(AppUser appUser) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

}
