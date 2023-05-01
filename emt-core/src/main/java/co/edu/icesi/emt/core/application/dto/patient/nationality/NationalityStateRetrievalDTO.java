package co.edu.icesi.emt.core.application.dto.patient.nationality;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.domain.model.patient.nationality.NationalityState;

public class NationalityStateRetrievalDTO {

    private final String nationality;
    private final int nationalityStateCode;
    private final String nationalityStateName;

    @JsonCreator
    public NationalityStateRetrievalDTO(@JsonProperty("nationality") String nationality,
            @JsonProperty("nationalityStateCode") int nationalityStateCode,
            @JsonProperty("nationalityStateName") String nationalityStateName) {
        this.nationality = nationality;
        this.nationalityStateCode = nationalityStateCode;
        this.nationalityStateName = nationalityStateName;
    }

    public String getNationality() {
        return nationality;
    }

    public int getNationalityStateCode() {
        return nationalityStateCode;
    }

    public String getNationalityStateName() {
        return nationalityStateName;
    }

    public static NationalityStateRetrievalDTO from(NationalityState nationalityState) {
        return new NationalityStateRetrievalDTO(nationalityState.getNationality(),
                nationalityState.getMigratoryState().getId(), nationalityState.getMigratoryState().getName());
    }
}
