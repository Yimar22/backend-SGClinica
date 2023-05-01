package co.edu.icesi.emt.auth.application.dto.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class LoginResponseDTO {

    private final String username;
    private final String token;

    private final String bearer = "Bearer";

    private final Collection<? extends GrantedAuthority> authorities;

    public LoginResponseDTO(final String username,
            final String token, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.token = token;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getBearer() {
        return bearer;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO [authorities=" + authorities + ", bearer=" + bearer + ", token=" + token
                + ", username=" + username + "]";
    }
}