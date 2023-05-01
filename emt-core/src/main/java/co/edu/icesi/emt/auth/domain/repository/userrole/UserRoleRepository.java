package co.edu.icesi.emt.auth.domain.repository.userrole;

import java.util.List;

import co.edu.icesi.emt.auth.domain.model.role.Role;
import co.edu.icesi.emt.auth.domain.model.user.User;

public interface UserRoleRepository {
    void save(User user, Role role);

    boolean userHasRole(User user, Role role);

    List<String> findUserRoleIdsByUsername(String username);

    void deleteUserRoleByUsernameAndRoleId(User user, Role role);
}
