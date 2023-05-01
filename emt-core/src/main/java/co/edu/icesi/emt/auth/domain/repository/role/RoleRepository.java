package co.edu.icesi.emt.auth.domain.repository.role;

import java.util.List;

import co.edu.icesi.emt.auth.domain.model.role.Role;

public interface RoleRepository {

    void save(String name, String description);

    Role findById(String id);

    List<Role> findAll();

    void deleteById(String id);
}
