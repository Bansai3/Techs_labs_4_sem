package ru.fedotov.owners.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.fedotov.jpa.users.entity.User;
import ru.fedotov.jpa.users.role.Role;
import ru.fedotov.jpa.users.status.Status;

import java.util.Collection;

public class SecurityUser implements UserDetails {

    private String userName;
    private String password;
    private boolean accountNonLocked;
    private Role role;

    public SecurityUser(String userName, String password, boolean accountNonLocked, Role role) {
        this.userName = userName;
        this.password = password;
        this.accountNonLocked = accountNonLocked;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities()
        );
    }
}
