package co.edu.icesi.emt.core.domain.repository.patient.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.icesi.emt.core.domain.model.patient.Patient;
import co.edu.icesi.emt.core.domain.model.patient.PatientPreview;
import co.edu.icesi.emt.core.domain.model.patient.nationality.MigratoryState;
import co.edu.icesi.emt.core.domain.model.patient.nationality.NationalityState;
import co.edu.icesi.emt.core.domain.repository.patient.PatientRepository;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private static final String PATIENT_TABLE = "patient";

    private static final String ID = "id";
    private static final String CREATION_DATE = "creation_date";

    private static final String NATIONALITY = "nationality";
    private static final String MIGRATORY_STATE = "migratory_state";

    private static final String PATIENT_COLUMNS = ID + ", " + CREATION_DATE + ", " + NATIONALITY + ", "
            + MIGRATORY_STATE;

    private static final String SELECT_ALL = "SELECT " + PATIENT_COLUMNS + " FROM " + PATIENT_TABLE;
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE " + ID + " = ?";

    private static final String INSERT = "INSERT INTO " + PATIENT_TABLE + " (" + PATIENT_COLUMNS
            + ") VALUES (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE " + PATIENT_TABLE + " SET " + NATIONALITY + " = ?, "
            + MIGRATORY_STATE + " = ? WHERE " + ID + " = ?";

    private static final String EXIST_BY_ID = "SELECT EXISTS(SELECT 1 FROM " + PATIENT_TABLE + " WHERE " + ID + " = ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Patient findById(String id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID,
                    this::parse, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(Patient patient) {
        if (this.existsById(patient.getId())) {
            this.update(patient);
        } else {
            jdbcTemplate.update(INSERT, patient.getId(), patient.getCreationDate(),
                    patient.getNationalityState().getNationality(),
                    patient.getNationalityState().getMigratoryState().getId());
        }
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Patient patient) {
        jdbcTemplate.update(UPDATE,
                patient.getNationalityState().getNationality(),
                patient.getNationalityState().getMigratoryState().getId(),
                patient.getId());
    }

    @Override
    public List<PatientPreview> findAll() {
        try {
            return jdbcTemplate.query(SELECT_ALL,
                    this::parsePreview);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean existsById(String id) {
        return jdbcTemplate.queryForObject(EXIST_BY_ID, Boolean.class, id);
    }

    private Patient parse(final ResultSet rs, final int rowNum) throws SQLException {

        String id = rs.getString(ID);
        Instant creationDate = rs.getTimestamp(CREATION_DATE).toInstant();

        Patient patient = new Patient(id, creationDate);

        String nationality = rs.getString(NATIONALITY);
        MigratoryState migratoryState = MigratoryState.byId(rs.getInt(MIGRATORY_STATE));
        NationalityState nationalityState = new NationalityState(nationality, migratoryState);

        patient.setNationalityState(nationalityState);

        return patient;
    }

    private PatientPreview parsePreview(final ResultSet rs, final int rowNum) throws SQLException {
        String id = rs.getString(ID);
        Instant creationDate = rs.getTimestamp(CREATION_DATE).toInstant();

        return new PatientPreview(id, creationDate);
    }
}
