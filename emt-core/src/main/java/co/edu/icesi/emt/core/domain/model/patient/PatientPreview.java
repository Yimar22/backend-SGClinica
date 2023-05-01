package co.edu.icesi.emt.core.domain.model.patient;

import java.time.Instant;

import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformationPreview;

public class PatientPreview {

    private final String id;
    private final Instant creationDate;
    private PersonalInformationPreview personalInformation;

    public PatientPreview(String id, Instant creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public PatientPreview(String id, Instant creationDate, PersonalInformationPreview personalInformation) {
        this.id = id;
        this.creationDate = creationDate;
        this.personalInformation = personalInformation;
    }

    public String getId() {
        return id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public PersonalInformationPreview getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformationPreview personalInformation) {
        this.personalInformation = personalInformation;
    }
}
