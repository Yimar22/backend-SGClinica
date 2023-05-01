package co.edu.icesi.emt.core.domain.repository.clinicalhistory.format.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormat;
import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatLight;
import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatRetrieval;
import co.edu.icesi.emt.core.domain.repository.clinicalhistory.format.ClinicalHistoryFormatRepository;

@Repository
public class ClinicalHistoryFormatRepositoryImpl implements ClinicalHistoryFormatRepository {

    private static final String CLINICAL_HISTORY_FORMAT_TABLE = "clinical_history_format";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String ENABLED = "enabled";
    private static final String CREATED_AT = "created_at";
    private static final String PAYLOAD = "payload";

    private static final String CLINICAL_HISTORY_FORMAT_COLUMNS = ID + ", " + NAME + ", " + DESCRIPTION + ", " + ENABLED
            + ", " + CREATED_AT + ", " + PAYLOAD;

    private static final String INSERT_COLUMNS = NAME + ", " + DESCRIPTION + ", " + ENABLED + ", " + CREATED_AT + ", "
            + PAYLOAD;

    private static final String INSERT_CLINICAL_HISTORY_FORMAT = "INSERT INTO " + CLINICAL_HISTORY_FORMAT_TABLE + " ("
            + INSERT_COLUMNS + ") VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_CLINICAL_HISTORY_FORMAT_BY_ID = "SELECT " + CLINICAL_HISTORY_FORMAT_COLUMNS
            + " FROM "
            + CLINICAL_HISTORY_FORMAT_TABLE + " WHERE " + ID + " = ?";

    private static final String SELECT_COLUMNS_WITHOUT_PAYLOAD = ID + ", " + NAME + ", " + DESCRIPTION + ", " + ENABLED
            + ", "
            + CREATED_AT;

    private static final String SELECT_ALL_CLINICAL_HISTORY_FORMATS = "SELECT " + SELECT_COLUMNS_WITHOUT_PAYLOAD
            + " FROM "
            + CLINICAL_HISTORY_FORMAT_TABLE;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClinicalHistoryFormatRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ClinicalHistoryFormat clinicalHistoryFormatCreation) {
        jdbcTemplate.update(INSERT_CLINICAL_HISTORY_FORMAT, clinicalHistoryFormatCreation.getName(),
                clinicalHistoryFormatCreation.getDescription(), clinicalHistoryFormatCreation.isEnabled(),
                Timestamp.from(Instant.now()),
                clinicalHistoryFormatCreation.getPayload());
    }

    @Override
    public ClinicalHistoryFormatRetrieval findById(String id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CLINICAL_HISTORY_FORMAT_BY_ID,
                    this::parse, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ClinicalHistoryFormatLight> findAll() {
        return jdbcTemplate.query(SELECT_ALL_CLINICAL_HISTORY_FORMATS,
                this::parseLight);
    }

    @Override
    public void disableById(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enableById(String id) {
        // TODO Auto-generated method stub

    }

    private ClinicalHistoryFormatRetrieval parse(final ResultSet rs, final int rowNum) throws SQLException {
        return new ClinicalHistoryFormatRetrieval(rs.getString(ID), rs.getString(NAME), rs.getString(DESCRIPTION),
                rs.getBoolean(ENABLED), rs.getTimestamp(CREATED_AT).toInstant(), rs.getString(PAYLOAD));
    }

    private ClinicalHistoryFormatLight parseLight(final ResultSet rs, final int rowNum) throws SQLException {
        return new ClinicalHistoryFormatLight(rs.getString(ID), rs.getString(NAME), rs.getString(DESCRIPTION),
                rs.getBoolean(ENABLED), rs.getTimestamp(CREATED_AT).toInstant());
    }
}
