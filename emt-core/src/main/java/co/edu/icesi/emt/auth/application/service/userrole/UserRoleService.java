package co.edu.icesi.emt.auth.application.service.userrole;

import java.util.List;

import co.edu.icesi.emt.auth.domain.model.role.Role;
import co.edu.icesi.emt.auth.domain.model.user.User;

public interface UserRoleService {
    void save(User user, Role role);

    void save(User user, String[] roles);

    boolean userHasRole(User user, Role role);

    List<Role> findUserRoleIdsByUsername(String username);

    void deleteUserRoleByUsernameAndRoleId(User user, Role role);
}
