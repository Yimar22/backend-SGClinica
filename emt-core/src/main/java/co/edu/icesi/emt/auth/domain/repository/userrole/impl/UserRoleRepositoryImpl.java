package co.edu.icesi.emt.auth.domain.repository.userrole.impl;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.icesi.emt.auth.domain.model.role.Role;
import co.edu.icesi.emt.auth.domain.model.user.User;
import co.edu.icesi.emt.auth.domain.repository.userrole.UserRoleRepository;

@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository {

    private static final String SCHEMA = "emt";
    private static final String USER_ROLE_TABLE = "user_role";
    private static final String USER_ROLE_FULL_TABLE_NAME = SCHEMA + "." + USER_ROLE_TABLE;

    private static final String USERUSERNAME = "userusername";
    private static final String ROLENAME = "rolename";

    private static final String SELECT_USER_ROLE_TABLE_COLUMNS = USERUSERNAME + ", " + ROLENAME;
    private static final String INSERT_USER_ROLE_TABLE_COLUMNS = USERUSERNAME + ", " + ROLENAME;

    private static final String SELECT_FROM_USER_ROLE = "SELECT " + SELECT_USER_ROLE_TABLE_COLUMNS + " FROM "
            + USER_ROLE_FULL_TABLE_NAME;
    private static final String SELECT_FROM_USER_ROLE_WHERE_USERNAME = SELECT_FROM_USER_ROLE + " WHERE " + USERUSERNAME
            + " = ?";

    private static final String SELECT_FROM_USER_ROLE_WHERE_USERNAME_AND_ID = SELECT_FROM_USER_ROLE + " WHERE "
            + USERUSERNAME + " = ? AND " + ROLENAME + " = ?";

    private static final String INSERT_INTO_USER_ROL = "INSERT INTO " + USER_ROLE_FULL_TABLE_NAME + " ("
            + INSERT_USER_ROLE_TABLE_COLUMNS + ") VALUES ( ?, ?)";

    private static final String DELETE_FROM_USER_ROLE_WHERE_USERNAME_AND_ID = "DELETE FROM " + USER_ROLE_FULL_TABLE_NAME
            + " WHERE "
            + USERUSERNAME
            + " = ? AND " + ROLENAME + " = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user, Role role) {
        jdbcTemplate.update(INSERT_INTO_USER_ROL, new Object[] { user.getUsername(), role.getName() });
    }

    @Override
    public boolean userHasRole(User user, Role role) {
        return jdbcTemplate.queryForRowSet(SELECT_FROM_USER_ROLE_WHERE_USERNAME_AND_ID,
                new Object[] { user.getUsername(), role.getName() }).first();
    }

    @Override
    public List<String> findUserRoleIdsByUsername(String username) {
        try {
            return jdbcTemplate.query(SELECT_FROM_USER_ROLE_WHERE_USERNAME,
                    (ResultSet rs, int rowNum) -> {
                        return rs.getString(ROLENAME);
                    }, new Object[] { username });
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteUserRoleByUsernameAndRoleId(User user, Role role) {
        jdbcTemplate.update(DELETE_FROM_USER_ROLE_WHERE_USERNAME_AND_ID,
                new Object[] { user.getUsername(), role.getName() });
    }
}
