package co.edu.icesi.emt.auth.application.service.userrole.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.emt.auth.application.service.userrole.UserRoleService;
import co.edu.icesi.emt.auth.domain.model.role.Role;
import co.edu.icesi.emt.auth.domain.model.user.User;
import co.edu.icesi.emt.auth.domain.repository.role.RoleRepository;
import co.edu.icesi.emt.auth.domain.repository.userrole.UserRoleRepository;
import co.edu.icesi.emt.auth.domain.repository.userrole.impl.UserRoleRepositoryImpl;

@Service
public class UserRoleServiceImpl implements UserRoleService {// TODO: To implements

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepositoryImpl userRoleRepository, RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(User user, Role role) {
        if (user == null) {
            throw new IllegalArgumentException("User was not found");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role was not found");
        }

        userRoleRepository.save(user, role);
    }

    @Override
    public void save(User user, String[] roles) {
        for (String roleId : roles) {
            this.save(user, roleRepository.findById(roleId));
        }
    }

    @Override
    public boolean userHasRole(User user, Role role) {
        if (user == null) {
            throw new IllegalArgumentException("User was not found");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role was not found");
        }
        return userRoleRepository.userHasRole(user, role);
    }

    @Override
    public List<Role> findUserRoleIdsByUsername(String username) {
        List<Role> roles = new ArrayList<Role>();

        for (String roleId : userRoleRepository.findUserRoleIdsByUsername(username)) {
            roles.add(roleRepository.findById(roleId));
        }

        return roles;
    }

    @Override
    public void deleteUserRoleByUsernameAndRoleId(User user, Role role) {
        userRoleRepository.deleteUserRoleByUsernameAndRoleId(user, role);
    }
}