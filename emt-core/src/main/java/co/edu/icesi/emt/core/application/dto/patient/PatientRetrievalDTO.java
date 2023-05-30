package co.edu.icesi.emt.core.application.dto.patient;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.application.dto.patient.nationality.NationalityStateRetrievalDTO;
import co.edu.icesi.emt.core.application.dto.personalinformation.PersonalInformationRetrievalDTO;
import co.edu.icesi.emt.core.domain.model.patient.Patient;

public class PatientRetrievalDTO {

    private final String id;
    private final Instant creationDate;
    private final PersonalInformationRetrievalDTO personalInformation;

    private final NationalityStateRetrievalDTO nationalityStateRetrievalDTO;

    @JsonCreator
    public PatientRetrievalDTO(@JsonProperty("id") String id, @JsonProperty("creationDate") Instant creationDate,
            @JsonProperty("personalInformation") PersonalInformationRetrievalDTO personalInformation,
            @JsonProperty("nationalityState") NationalityStateRetrievalDTO nationalityStateRetrievalDTO) {
        this.id = id;
        this.creationDate = creationDate;
        this.personalInformation = personalInformation;
        this.nationalityStateRetrievalDTO = nationalityStateRetrievalDTO;
    }

    public String getId() {
        return id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public PersonalInformationRetrievalDTO getPersonalInformation() {
        return personalInformation;
    }

    public NationalityStateRetrievalDTO getNationalityStateRetrievalDTO() {
        return nationalityStateRetrievalDTO;
    }

    public static PatientRetrievalDTO from(Patient patient) {
        return new PatientRetrievalDTO(patient.getId(), patient.getCreationDate(),
                PersonalInformationRetrievalDTO.from(patient.getPersonalInformation()),
                NationalityStateRetrievalDTO.from(patient.getNationalityState()));
    }
}
