package co.edu.icesi.emt.auth.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.common.exception.model.UsernameAlreadyExistsException;

@Component
public class UserNotExistValidator {

    private final UserService userService;

    @Autowired
    public UserNotExistValidator(UserService userService) {
        this.userService = userService;
    }

    public void validate(String username) throws UsernameAlreadyExistsException {
        if (userService.userExist(username)) {
            throw new UsernameAlreadyExistsException();
        }
    }

}
