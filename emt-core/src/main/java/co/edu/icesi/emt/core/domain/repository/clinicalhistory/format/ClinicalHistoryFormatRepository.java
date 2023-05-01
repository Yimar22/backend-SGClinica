package co.edu.icesi.emt.core.domain.repository.clinicalhistory.format;

import java.util.List;

import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormat;
import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatLight;
import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatRetrieval;

public interface ClinicalHistoryFormatRepository {

    void save(ClinicalHistoryFormat clinicalHistoryFormatCreation);

    ClinicalHistoryFormatRetrieval findById(String id);

    List<ClinicalHistoryFormatLight> findAll();

    void disableById(String id);

    void enableById(String id);
}
