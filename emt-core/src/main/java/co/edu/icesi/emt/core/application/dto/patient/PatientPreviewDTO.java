package co.edu.icesi.emt.core.application.dto.patient;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.application.dto.personalinformation.PersonalInformationPreviewDTO;
import co.edu.icesi.emt.core.domain.model.patient.PatientPreview;

public class PatientPreviewDTO {

    private final String id;
    private final Instant creationDate;
    private final PersonalInformationPreviewDTO personalInformation;

    @JsonCreator
    public PatientPreviewDTO(@JsonProperty("id") String id, @JsonProperty("creationDate") Instant creationDate,
            @JsonProperty("personalInformation") PersonalInformationPreviewDTO personalInformation) {
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

    public PersonalInformationPreviewDTO getPersonalInformation() {
        return personalInformation;
    }

    public static PatientPreviewDTO from(PatientPreview patientPreview) {
        return new PatientPreviewDTO(patientPreview.getId(), patientPreview.getCreationDate(),
                PersonalInformationPreviewDTO.from(patientPreview.getPersonalInformation()));
    }

    public static List<PatientPreviewDTO> from(List<PatientPreview> patientPreviews) {
        return patientPreviews.stream().map(PatientPreviewDTO::from).toList();
    }
}
