package co.edu.icesi.emt.auth.security.service.userdetails.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.auth.application.service.user.impl.UserServiceImpl;
import co.edu.icesi.emt.auth.security.entity.MainUser;
import co.edu.icesi.emt.common.exception.model.UserNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return MainUser.build(this.userService.findByUsername(username),
                    this.userService.findUserRolesByUsername(username));
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}