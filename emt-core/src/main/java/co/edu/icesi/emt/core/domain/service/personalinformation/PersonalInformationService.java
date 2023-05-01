package co.edu.icesi.emt.core.domain.service.personalinformation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformation;
import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformationPreview;
import co.edu.icesi.emt.core.domain.repository.personalinformation.PersonalInformationRepository;
import co.edu.icesi.emt.core.domain.repository.personalinformation.implementation.PersonalInformationRepositoryImpl;

@Service
public class PersonalInformationService {

    private final PersonalInformationRepository personalInformationRepository;

    @Autowired
    public PersonalInformationService(PersonalInformationRepositoryImpl personalInformationRepository) {
        this.personalInformationRepository = personalInformationRepository;
    }

    public PersonalInformation findById(String id) {
        return this.personalInformationRepository.findById(id);
    }

    public PersonalInformationPreview findPreviewById(String id) {
        return this.personalInformationRepository.findPreviewById(id);
    }

    public List<PersonalInformationPreview> findAll() {
        return this.personalInformationRepository.findAll();
    }

    public void save(PersonalInformation personalInformation) {
        if (this.findById(personalInformation.getId()) == null) {
            this.personalInformationRepository.save(personalInformation);
        } else {
            this.personalInformationRepository.update(personalInformation);
        }
    }

    public void deleteById(String id) {
        this.personalInformationRepository.delete(id);
    }

    public void update(PersonalInformation personalInformation) {
        this.personalInformationRepository.update(personalInformation);
    }

    public boolean existsById(String id) {
        return this.personalInformationRepository.findPreviewById(id) != null;
    }
}
