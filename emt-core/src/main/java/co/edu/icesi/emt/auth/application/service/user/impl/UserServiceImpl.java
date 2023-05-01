package co.edu.icesi.emt.auth.application.service.user.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.auth.application.service.userrole.UserRoleService;
import co.edu.icesi.emt.auth.domain.model.role.Role;
import co.edu.icesi.emt.auth.domain.model.user.User;
import co.edu.icesi.emt.auth.domain.repository.user.UserRepository;
import co.edu.icesi.emt.common.exception.model.RootAdminCanNotBeRemovedException;
import co.edu.icesi.emt.common.exception.model.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private static final String ROOT_ADMIN_USERNAME = "admin";

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    @Transactional
    @Override
    public void save(String username, String password, String[] roles) {
        this.userRepository.save(username, password);
        if (roles != null && roles.length > 0) {
            this.userRoleService.save(userRepository.findByUsername(username), roles);
        }
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteByUsername(String username) throws RootAdminCanNotBeRemovedException {
        if (username.equals(ROOT_ADMIN_USERNAME)) {
            throw new RootAdminCanNotBeRemovedException();
        }
        this.userRepository.deleteByUsername(username);
    }

    @Override
    public Set<Role> findUserRolesByUsername(String username) {
        return this.userRoleService.findUserRoleIdsByUsername(username).stream().collect(Collectors.toSet());
    }

    @Override
    public void changePassword(String username, String password) {
        this.userRepository.changePassword(username, password);
    }

    @Override
    public void setUserStatus(String username, boolean status) {
        this.userRepository.setUserStatus(username, status);
    }

    @Override
    public boolean isAccountEnabled(String username) {
        return this.userRepository.getUserAccountStatus(username);
    }

    @Override
    public void saveLastLogin(String username) {
        this.userRepository.saveLastLogin(username);
    }

    @Override
    public boolean userExist(String username) {
        try {
            findByUsername(username);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }
}