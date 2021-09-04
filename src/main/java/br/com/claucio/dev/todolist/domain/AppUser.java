package br.com.claucio.dev.todolist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_user")
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Nome obrigatório")
    private String username;

    @NotEmpty(message = "Senha obrigatória")
    private String password;

    @NotEmpty(message = "Nome de exibição obrigatória")
    private String displayName;

    public AppUser(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public AppUser() {
    }
}
