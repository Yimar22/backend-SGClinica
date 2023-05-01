package co.edu.icesi.emt.auth.application.service.role;

import java.util.List;

import co.edu.icesi.emt.auth.domain.model.role.Role;

public interface RoleService {
    void save(String name, String description);

    Role findById(String name);

    List<Role> findAll();

    void deleteRole(Role role);
}