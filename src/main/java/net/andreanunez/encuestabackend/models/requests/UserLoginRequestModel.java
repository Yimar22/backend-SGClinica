package net.andreanunez.encuestabackend.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Esta clase sirve para que con ella se haga un mapeo del request de un login
 * con esta clase
 * se utiliza en la clase AuthenticationFilter
 */
@Data
public class UserLoginRequestModel {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;
}
