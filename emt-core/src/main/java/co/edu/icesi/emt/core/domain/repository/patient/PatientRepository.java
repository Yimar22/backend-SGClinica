package co.edu.icesi.emt.core.domain.repository.patient;

import java.util.List;

import co.edu.icesi.emt.core.domain.model.patient.Patient;
import co.edu.icesi.emt.core.domain.model.patient.PatientPreview;

public interface PatientRepository {

    Patient findById(String id);

    void save(Patient patient);

    void delete(String id);

    void update(Patient patient);

    List<PatientPreview> findAll();

}
