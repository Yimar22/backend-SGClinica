package co.edu.icesi.emt.auth.util.validators;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.emt.auth.application.service.role.RoleService;
import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.auth.application.service.userrole.UserRoleService;
import co.edu.icesi.emt.auth.util.http.HttpRequestUtil;
import co.edu.icesi.emt.common.exception.model.UserIsNotAdminException;
import co.edu.icesi.emt.common.exception.model.UserNotFoundException;

@Component
public class UserAdminValidator {

    private static final String ADMIN_ROLE_NAME = "ADMIN";

    private final HttpRequestUtil httpRequestUtil;
    private final UserRoleService userRoleService;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserAdminValidator(HttpRequestUtil httpRequestUtil, UserRoleService userRoleService, UserService userService,
            RoleService roleService) {
        this.httpRequestUtil = httpRequestUtil;
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.roleService = roleService;
    }

    public void validate(final HttpServletRequest httpRequest) throws UserIsNotAdminException, UserNotFoundException {

        String userId = httpRequestUtil.getUserIdFromRequest(httpRequest);

        boolean isAdmin = userRoleService.userHasRole(userService.findByUsername(userId),
                roleService.findById(ADMIN_ROLE_NAME));

        if (!isAdmin) {
            throw new UserIsNotAdminException();
        }
    }
}
