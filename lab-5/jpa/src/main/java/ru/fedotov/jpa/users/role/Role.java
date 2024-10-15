package ru.fedotov.jpa.users.role;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private String role;

    Role(String role) {
        this.role = role;
    }
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role));
    }
}
