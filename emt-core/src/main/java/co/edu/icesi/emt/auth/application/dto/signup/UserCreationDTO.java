package co.edu.icesi.emt.auth.application.dto.signup;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreationDTO {

    private final String username;
    private final String password;

    private final String[] roles;

    @JsonCreator
    public UserCreationDTO(@JsonProperty("username") String username,
            @JsonProperty("password") String password, @JsonProperty("rolesIds") String[] roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String[] getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "SignupRequestDTO [password=" + password + ", roles=" + roles + ", username=" + username + "]";
    }
}