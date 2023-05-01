package co.edu.icesi.emt.core.domain.repository.personalinformation;

import java.util.List;

import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformation;
import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformationPreview;

public interface PersonalInformationRepository {

    PersonalInformation findById(String id);

    void save(PersonalInformation personalInformation);

    void delete(String id);

    void update(PersonalInformation personalInformation);

    List<PersonalInformationPreview> findAll();

    PersonalInformationPreview findPreviewById(String id);
}
