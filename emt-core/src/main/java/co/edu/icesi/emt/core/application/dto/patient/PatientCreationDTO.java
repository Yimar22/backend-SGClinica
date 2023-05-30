package co.edu.icesi.emt.core.application.dto.patient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.application.dto.patient.nationality.NationalityStateModificationDTO;
import co.edu.icesi.emt.core.application.dto.personalinformation.PersonalInformationModificationDTO;
import co.edu.icesi.emt.core.domain.model.patient.Patient;

public class PatientCreationDTO {

    private final String id;
    private final PersonalInformationModificationDTO personalInformation;

    private final NationalityStateModificationDTO nationalityState;

    @JsonCreator
    public PatientCreationDTO(@JsonProperty("id") String id,
            @JsonProperty("personalInformation") PersonalInformationModificationDTO personalInformation,
            @JsonProperty("nationalityState") NationalityStateModificationDTO nationalityState) {
        this.id = id;
        this.personalInformation = personalInformation;
        this.nationalityState = nationalityState;
    }

    public String getId() {
        return id;
    }

    public PersonalInformationModificationDTO getPersonalInformation() {
        return personalInformation;
    }

    public NationalityStateModificationDTO getNationalityState() {
        return nationalityState;
    }

    public static Patient fromDTO(PatientCreationDTO dto) {
        return new Patient(dto.getId(), PersonalInformationModificationDTO.fromDTO(dto.getPersonalInformation()),
                NationalityStateModificationDTO.fromDTO(dto.getNationalityState()));
    }
}
