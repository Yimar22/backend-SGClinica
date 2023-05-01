package co.edu.icesi.emt.auth.application.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.emt.auth.application.service.role.RoleService;
import co.edu.icesi.emt.auth.domain.model.role.Role;
import co.edu.icesi.emt.auth.domain.repository.role.RoleRepository;
import co.edu.icesi.emt.auth.domain.repository.role.impl.RoleRepositoryImpl;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepositoryImpl roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(String name, String description) {
        // TODO: Add other validations
        this.roleRepository.save(name, description);
    }

    @Override
    public Role findById(String id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public void deleteRole(Role role) {
        this.roleRepository.deleteById(role.getName());
    }
}
