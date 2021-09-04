package br.com.claucio.dev.todolist.controller;

import br.com.claucio.dev.todolist.domain.AppUser;
import br.com.claucio.dev.todolist.service.CreatedUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/new")
@AllArgsConstructor
public class CreatedUserController {

    private final CreatedUserService createdUserService;

    @PostMapping()
    public ResponseEntity<AppUser> save(@RequestBody @Valid AppUser appUser){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        encoder.encode(appUser.getPassword());
        return new ResponseEntity<>(createdUserService.createdUser(appUser), HttpStatus.CREATED);
    }
}
