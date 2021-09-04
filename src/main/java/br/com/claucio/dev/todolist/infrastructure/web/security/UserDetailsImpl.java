package br.com.claucio.dev.todolist.infrastructure.web.security;

import br.com.claucio.dev.todolist.domain.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final String username;
    private final String password;
    private final String displayName;

    public UserDetailsImpl(AppUser appUser) {
        this.username = appUser.getUsername();
        this.password = appUser.getPassword();
        this.displayName = appUser.getDisplayName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
