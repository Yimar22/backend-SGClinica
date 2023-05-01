package co.edu.icesi.emt.auth.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.common.exception.model.UserAccountDisabledException;
import co.edu.icesi.emt.common.exception.model.UserNotFoundException;

@Component
public class UserAccountEnabledValidator {

    private final UserService userService;

    @Autowired
    public UserAccountEnabledValidator(UserService userService) {
        this.userService = userService;
    }

    public void validate(String username) throws UserAccountDisabledException, UserNotFoundException {
        if (!userService.findByUsername(username).isEnabled()) {
            throw new UserAccountDisabledException();
        }
    }
}
