package br.com.claucio.dev.todolist.infrastructure.web.security;

import br.com.claucio.dev.todolist.domain.AppUser;
import br.com.claucio.dev.todolist.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

     private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser == null){
          throw new UsernameNotFoundException(username);
        }
       return new UserDetailsImpl(appUser);
    }
}
