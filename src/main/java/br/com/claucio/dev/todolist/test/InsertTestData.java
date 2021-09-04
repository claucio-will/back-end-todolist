package br.com.claucio.dev.todolist.test;

import br.com.claucio.dev.todolist.domain.AppUser;
import br.com.claucio.dev.todolist.domain.task.Task;
import br.com.claucio.dev.todolist.repositories.AppUserRepository;
import br.com.claucio.dev.todolist.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class InsertTestData {

    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        AppUser appUser1 = new AppUser("Claucio", encoder.encode("123"),"Claucio dev");
        appUserRepository.save(appUser1);

        AppUser appUser2 = new AppUser("Willian", encoder.encode("321"),"Willian dev");
        appUserRepository.save(appUser2);

        LocalDate baseDate = LocalDate.parse("2025-02-01");

        for (int i = 0; i <= 5; i++){
            Task task = new Task(String.format("Tarefa do %s #%d", appUser1.getUsername(), i), baseDate.plusDays(i), false);
            task.setAppUser(appUser1);
            taskRepository.save(task);
        }

        for (int i = 0; i <= 5; i++){
            Task task = new Task(String.format("Tarefa do %s #%d", appUser2.getUsername(), i), baseDate.plusDays(i), false);
            task.setAppUser(appUser2);
            taskRepository.save(task);
        }

    }
}
