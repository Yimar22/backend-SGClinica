package co.edu.icesi.emt.auth.application.dto.resetpassword;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetPasswordRequestDTO {

    private final String username;
    private final String password;

    @JsonCreator
    public ResetPasswordRequestDTO(@JsonProperty("username") String username,
            @JsonProperty("password") String password) {
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
        return "ResetPasswordRequestDTO [username=" + username + "]";
    }
}
