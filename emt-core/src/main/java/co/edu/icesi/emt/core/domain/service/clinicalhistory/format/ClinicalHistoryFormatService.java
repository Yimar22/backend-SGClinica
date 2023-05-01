package co.edu.icesi.emt.core.domain.service.clinicalhistory.format;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormat;
import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatLight;
import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatRetrieval;
import co.edu.icesi.emt.core.domain.repository.clinicalhistory.format.ClinicalHistoryFormatRepository;

@Service
public class ClinicalHistoryFormatService {

    private final ClinicalHistoryFormatRepository clinicalHistoryFormatRepository;

    @Autowired
    public ClinicalHistoryFormatService(ClinicalHistoryFormatRepository clinicalHistoryFormatRepository) {
        this.clinicalHistoryFormatRepository = clinicalHistoryFormatRepository;
    }

    public List<ClinicalHistoryFormatLight> findAll() {
        return clinicalHistoryFormatRepository.findAll();
    }

    public ClinicalHistoryFormatRetrieval findById(String id) {
        return clinicalHistoryFormatRepository.findById(id);
    }

    public void save(ClinicalHistoryFormat clinicalHistoryFormat) {
        clinicalHistoryFormatRepository.save(clinicalHistoryFormat);
    }
}
