package co.edu.icesi.emt.auth.domain.repository.user;

import java.util.List;

import co.edu.icesi.emt.auth.domain.model.user.User;

public interface UserRepository {
    void save(String username, String password);

    User findByUsername(String username);

    List<User> findAll();

    void deleteByUsername(String username);

    void changePassword(String username, String password);

    void setUserStatus(String username, boolean status);

    boolean getUserAccountStatus(String username);

    void saveLastLogin(String username);
}
