package co.edu.icesi.emt.core.domain.model.patient;

import java.time.Instant;

import co.edu.icesi.emt.core.domain.model.personalinformation.PersonalInformation;
import co.edu.icesi.emt.core.domain.model.patient.nationality.NationalityState;

public class Patient {

    private final String id;
    private Instant creationDate;
    private PersonalInformation personalInformation;
    private NationalityState nationalityState;

    public Patient(String id, Instant creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public Patient(String id, Instant creationDate, PersonalInformation personalInformation,
            NationalityState nationalityState) {
        this.id = id;
        this.creationDate = creationDate;
        this.personalInformation = personalInformation;
        this.nationalityState = nationalityState;
    }

    public Patient(String id, PersonalInformation personalInformation,
            NationalityState nationalityState) {
        this.id = id;
        this.personalInformation = personalInformation;

        this.nationalityState = nationalityState;
    }

    public String getId() {
        return id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public NationalityState getNationalityState() {
        return nationalityState;
    }

    public void setNationalityState(NationalityState nationalityState) {
        this.nationalityState = nationalityState;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }
}
