package co.edu.icesi.emt.auth.application.controller.role;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.emt.auth.application.dto.role.RoleDTO;
import co.edu.icesi.emt.auth.application.service.role.RoleService;
import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.auth.application.service.userrole.UserRoleService;
import co.edu.icesi.emt.auth.application.service.userrole.impl.UserRoleServiceImpl;
import co.edu.icesi.emt.auth.util.validators.UserAdminValidator;
import co.edu.icesi.emt.common.exception.model.UserIsNotAdminException;
import co.edu.icesi.emt.common.exception.model.UserNotFoundException;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final UserRoleService userRoleService;
    private final UserService userService;
    private final RoleService roleService;

    private final UserAdminValidator userAdminValidator;

    @Autowired
    public RoleController(UserRoleServiceImpl userRoleService, UserService userService, RoleService roleService,
            UserAdminValidator userAdminValidator) {
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.roleService = roleService;
        this.userAdminValidator = userAdminValidator;
    }

    @GetMapping()
    public ResponseEntity<List<RoleDTO>> getRoles(final HttpServletRequest request)
            throws UserIsNotAdminException, UserNotFoundException {
        userAdminValidator.validate(request);

        return new ResponseEntity<List<RoleDTO>>(RoleDTO.from(roleService.findAll()), HttpStatus.OK);
    }

    @PostMapping("{roleName}/user/{id}")
    public ResponseEntity<String> addUserRole(@PathVariable("roleName") final String roleName,
            @PathVariable("id") final String id, final HttpServletRequest request)
            throws UserIsNotAdminException, UserNotFoundException {

        userAdminValidator.validate(request);

        userRoleService.save(userService.findByUsername(id), roleService.findById(roleName));

        return new ResponseEntity<String>("Role added to user with id " + id, HttpStatus.OK);
    }

    @DeleteMapping("{roleName}/user/{id}")
    public ResponseEntity<String> deleteUserRole(@PathVariable("roleName") final String roleName,
            @PathVariable("id") final String id, final HttpServletRequest request)
            throws UserIsNotAdminException, UserNotFoundException {

        userAdminValidator.validate(request);

        userRoleService.deleteUserRoleByUsernameAndRoleId(userService.findByUsername(id),
                roleService.findById(roleName));

        return new ResponseEntity<String>("Role removed from the user with id " + id, HttpStatus.OK);
    }
}