package co.edu.icesi.emt.auth.application.dto.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequestDTO {

    private final String username;
    private final String password;

    @JsonCreator
    public LoginRequestDTO(@JsonProperty("username") final String username,
            @JsonProperty("password") final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginRequestDTO [password=" + password + ", username=" + username + "]";
    }
}