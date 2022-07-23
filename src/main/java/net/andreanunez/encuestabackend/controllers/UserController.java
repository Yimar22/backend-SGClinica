package net.andreanunez.encuestabackend.controllers;

import javax.validation.Valid;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.andreanunez.encuestabackend.entities.UserEntity;
import net.andreanunez.encuestabackend.models.requests.UserRegisterRequestModel;
import net.andreanunez.encuestabackend.models.responses.UserRest;
import net.andreanunez.encuestabackend.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public UserRest createUser(@RequestBody @Valid UserRegisterRequestModel userModel) {

        UserEntity user = userService.createUser(userModel);

        UserRest userRest = new UserRest();

        // para copiar las propiedades que hacen match de userEntity a userRest
        BeanUtils.copyProperties(user, userRest);

        return userRest;

    }

    // obtener el usuario que está actualmente autenticado
    @GetMapping()
    public UserRest getUser(Authentication authentication) {
        String userEmail = authentication.getPrincipal().toString();

        UserEntity user = userService.getUser(userEmail);

        // mapeamos UserEntity hacia UserRest
        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(user, userRest);

        return userRest;

    }

}
