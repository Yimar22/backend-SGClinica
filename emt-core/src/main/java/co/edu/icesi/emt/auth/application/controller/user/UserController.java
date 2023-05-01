package co.edu.icesi.emt.auth.application.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.emt.auth.application.dto.signup.UserCreationDTO;
import co.edu.icesi.emt.auth.application.dto.user.UserBasicRetrievalDTO;
import co.edu.icesi.emt.auth.application.dto.user.UserDetailedRetrievalDTO;
import co.edu.icesi.emt.auth.application.dto.user.status.AccountStatusModificationDTO;
import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.auth.application.service.userrole.UserRoleService;
import co.edu.icesi.emt.auth.domain.model.user.User;
import co.edu.icesi.emt.auth.util.validators.UserAdminValidator;
import co.edu.icesi.emt.auth.util.validators.UserNotExistValidator;
import co.edu.icesi.emt.common.exception.model.UserIsNotAdminException;
import co.edu.icesi.emt.common.exception.model.UserNotFoundException;
import co.edu.icesi.emt.common.exception.model.UsernameAlreadyExistsException;
import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformationPreview;
import co.edu.icesi.emt.core.domain.service.personalinformation.PersonalInformationService;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private PersonalInformationService personalInformationService;
    private UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    private final UserAdminValidator userAdminValidator;
    private final UserNotExistValidator userNotExistValidator;

    @Autowired
    public UserController(UserAdminValidator userAdminValidator,
            UserNotExistValidator userNotExistValidator,
            UserRoleService userRoleService,
            UserService userService,
            PersonalInformationService personalInformationService,
            PasswordEncoder passwordEncoder) {
        this.userAdminValidator = userAdminValidator;
        this.userNotExistValidator = userNotExistValidator;
        this.userService = userService;
        this.personalInformationService = personalInformationService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<UserBasicRetrievalDTO>> getAllUsers(final HttpServletRequest request)
            throws UserIsNotAdminException, UserNotFoundException {
        userAdminValidator.validate(request);

        Map<String, String> fullNames = new HashMap<>();
        List<User> users = userService.findAll();

        users.forEach(user -> {
            PersonalInformationPreview personalInformation = personalInformationService
                    .findPreviewById(user.getUsername());

            if (personalInformation != null) {
                fullNames.put(user.getUsername(),
                        personalInformation.getFirstName() + " " + personalInformation.getLastName());
            } else {
                fullNames.put(user.getUsername(), "Sin nombre");
            }
        });

        return new ResponseEntity<List<UserBasicRetrievalDTO>>(UserBasicRetrievalDTO.from(users, fullNames),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody final UserCreationDTO userCreationDTO,
            final HttpServletRequest httpRequest)
            throws UserIsNotAdminException, UserNotFoundException, UsernameAlreadyExistsException {

        userAdminValidator.validate(httpRequest);
        userNotExistValidator.validate(userCreationDTO.getUsername());

        userService.save(userCreationDTO.getUsername(), passwordEncoder.encode(userCreationDTO.getPassword()),
                userCreationDTO.getRoles());

        return new ResponseEntity<String>(
                "User created: " + userService.findByUsername(userCreationDTO.getUsername()).toString(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Boolean> getUserStatus(@PathVariable("id") final String id,
            final HttpServletRequest httpRequest) throws UserIsNotAdminException, UserNotFoundException {
        userAdminValidator.validate(httpRequest);

        return new ResponseEntity<Boolean>(userService.isAccountEnabled(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> setUserStatus(@PathVariable("id") String id,
            @RequestBody final AccountStatusModificationDTO accountStatusModificationDTO,
            final HttpServletRequest httpRequest) throws UserIsNotAdminException, UserNotFoundException {

        userAdminValidator.validate(httpRequest);
        userService.setUserStatus(id, accountStatusModificationDTO.isEnabled());

        return new ResponseEntity<String>("User status changed for username: " + id, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailedRetrievalDTO> findUserById(@PathVariable("id") String id)
            throws UserNotFoundException {
        User user = userService.findByUsername(id);

        return new ResponseEntity<UserDetailedRetrievalDTO>(
                UserDetailedRetrievalDTO.from(user.getUsername(), user.getLastLogin(),
                        userRoleService.findUserRoleIdsByUsername(user.getUsername()), user.isEnabled()),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id, final HttpServletRequest httpRequest)
            throws Exception {

        userAdminValidator.validate(httpRequest);

        userService.deleteByUsername(id);

        return new ResponseEntity<String>("User deleted: " + id, HttpStatus.OK);
    }
}
