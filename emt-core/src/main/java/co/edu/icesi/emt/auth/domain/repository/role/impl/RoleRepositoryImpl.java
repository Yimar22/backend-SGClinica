package co.edu.icesi.emt.auth.domain.repository.role.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.icesi.emt.auth.domain.model.role.Role;
import co.edu.icesi.emt.auth.domain.repository.role.RoleRepository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private static final String SCHEMA = "emt";
    private static final String ROLE_TABLE = "role";
    private static final String ROLE_FULL_TABLE_NAME = SCHEMA + "." + ROLE_TABLE;

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    private static final String SELECT_ROLE_TABLE_COLUMNS = NAME + ", " + DESCRIPTION;
    private static final String INSERT_ROLE_TABLE_COLUMNS = NAME + ", " + DESCRIPTION;

    private static final String SELECT_FROM_ROLE = "SELECT " + SELECT_ROLE_TABLE_COLUMNS + " FROM "
            + ROLE_FULL_TABLE_NAME;
    private static final String SELECT_FROM_ROLE_WHERE_NAME = SELECT_FROM_ROLE + " WHERE " + NAME + " = ?";

    private static final String INSERT_INTO_ROLE = "INSERT INTO " + ROLE_FULL_TABLE_NAME + " ("
            + INSERT_ROLE_TABLE_COLUMNS + ") VALUES ( ?, ?)";

    private static final String DELETE_FROM_ROLE_WHERE_ID = "DELETE FROM " + ROLE_FULL_TABLE_NAME + " WHERE " + NAME
            + " = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(String name, String description) {
        jdbcTemplate.update(INSERT_INTO_ROLE, new Object[] { name, description });
    }

    @Override
    public Role findById(String id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_FROM_ROLE_WHERE_NAME,
                    this::parse, new Object[] { id });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Role> findAll() {
        try {
            return jdbcTemplate.query(SELECT_FROM_ROLE,
                    this::parse);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteById(String id) {
        jdbcTemplate.update(DELETE_FROM_ROLE_WHERE_ID, new Object[] { id });
    }

    private Role parse(final ResultSet rs, final int rowNum) throws SQLException {
        String name = rs.getString(NAME);
        String description = rs.getString(DESCRIPTION);

        return new Role(name, description);
    }
}
