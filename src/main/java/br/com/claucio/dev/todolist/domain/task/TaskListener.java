package br.com.claucio.dev.todolist.domain.task;

import br.com.claucio.dev.todolist.domain.AppUser;
import br.com.claucio.dev.todolist.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

@Component
public class TaskListener {

    private static AppUserRepository appUserRepository;

    @Autowired
    public  void onInit(AppUserRepository appUserRepository){
       TaskListener.appUserRepository = appUserRepository;
    }

    @PrePersist
    public void onPrePersistHandler(Task task){

        if (task.getAppUser() == null){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            AppUser appUser = appUserRepository.findByUsername(username);

            if (appUser == null){
                throw  new EntityNotFoundException("O Usuário não foi encotrado");
            }

            task.setAppUser(appUser);
        }

    }

}
