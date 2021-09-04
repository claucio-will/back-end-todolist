package br.com.claucio.dev.todolist.domain.task;

import br.com.claucio.dev.todolist.domain.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EntityListeners(TaskListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "A descrição da tarefa é obrigatória")
    @Length(min = 3, max = 60, message = "Tamanho da tarefa é inválido")
    private String description;

    @NotNull(message = "A data é obrigatória")
    @FutureOrPresent(message = "A data não pode estar no passado")
    private LocalDate whenTodo;

    private Boolean done = false;

    /**
     * Um AppUser pode ter varias tarefas, e uma tarefa pode ter apenas um AppUser
     * ManyToOne
     */
    @ManyToOne
    @JoinColumn(name = "app_user_id")
//    @NotNull(message = "O usuário da tarefa é obrigatório")
    @JsonIgnore
    private AppUser appUser;

    public Task(String description, LocalDate whenTodo, Boolean done) {
        this.description = description;
        this.whenTodo = whenTodo;
        this.done = done;
    }

    public Task() {
    }
}
