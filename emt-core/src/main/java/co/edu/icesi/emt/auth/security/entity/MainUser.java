package co.edu.icesi.emt.auth.security.entity;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.edu.icesi.emt.auth.domain.model.role.Role;
import co.edu.icesi.emt.auth.domain.model.user.User;

public class MainUser extends User implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public MainUser(String username, String password, Instant lastLogin, boolean enabled,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, lastLogin, enabled);
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static MainUser build(User user, Set<Role> roles) {
        List<GrantedAuthority> authorities = roles.stream().map(rol -> new SimpleGrantedAuthority(rol
                .getName())).collect(Collectors.toList()); // To convert enum Role in GrantedAuthorities

        return new MainUser(user.getUsername(), user.getPassword(), user.getLastLogin(), user.isEnabled(), authorities);
    }

    public static MainUser build(User user) {
        return new MainUser(user.getUsername(), user.getPassword(), user.getLastLogin(), user.isEnabled(), null);
    }
}