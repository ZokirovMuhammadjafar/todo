package jafar.task.config.security;

import jafar.task.entity.AuthRole;
import jafar.task.entity.AuthUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.*;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    @Getter
    private Long id;
    private String username;
    private String password;
    private UUID code;
    private boolean blocked;
    private boolean active;
    private Set<GrantedAuthority> authorities;


    public UserDetails(AuthUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.blocked = user.isBlocked();
        this.active = user.isActive();

    }




    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
