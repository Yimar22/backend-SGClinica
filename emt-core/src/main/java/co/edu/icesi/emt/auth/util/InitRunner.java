/* package co.edu.icesi.emt.auth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.edu.icesi.emt.auth.application.service.role.RoleService;
import co.edu.icesi.emt.auth.application.service.user.UserService;
import co.edu.icesi.emt.auth.application.service.userrole.UserRoleService;

@Component
public class InitRunner implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        roleService.save("ADMIN", "ADMIN");
        userService.save("admin", passwordEncoder.encode("testpassword"));
        userRoleService.save(userService.findByUsername("admin"), roleService.findById("ADMIN"));
    }
} */