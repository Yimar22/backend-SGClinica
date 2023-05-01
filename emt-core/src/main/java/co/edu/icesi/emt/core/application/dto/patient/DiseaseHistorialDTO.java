package co.edu.icesi.emt.core.application.dto.patient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.application.dto.disease.DiseaseRetrievalDTO;
import co.edu.icesi.emt.core.domain.model.disease.Disease;
import co.edu.icesi.emt.core.domain.model.patient.diseasehistorial.DiseaseHistorial;

public class DiseaseHistorialDTO {

    private final DiseaseRetrievalDTO firstDisease;
    private final DiseaseRetrievalDTO secondDisease;
    private final DiseaseRetrievalDTO thirdDisease;
    private final DiseaseRetrievalDTO fourthDisease;

    @JsonCreator
    public DiseaseHistorialDTO(@JsonProperty("firstDisease") DiseaseRetrievalDTO firstDisease,
            @JsonProperty("secondDisease") DiseaseRetrievalDTO secondDisease,
            @JsonProperty("thirdDisease") DiseaseRetrievalDTO thirdDisease,
            @JsonProperty("fourthDisease") DiseaseRetrievalDTO fourthDisease) {
        this.firstDisease = firstDisease;
        this.secondDisease = secondDisease;
        this.thirdDisease = thirdDisease;
        this.fourthDisease = fourthDisease;
    }

    public DiseaseRetrievalDTO getFirstDisease() {
        return firstDisease;
    }

    public DiseaseRetrievalDTO getSecondDisease() {
        return secondDisease;
    }

    public DiseaseRetrievalDTO getThirdDisease() {
        return thirdDisease;
    }

    public DiseaseRetrievalDTO getFourthDisease() {
        return fourthDisease;
    }

    public static DiseaseHistorialDTO from(DiseaseHistorial diseaseHistorial) {
        return new DiseaseHistorialDTO(
                DiseaseRetrievalDTO.from(diseaseHistorial.getFirstDisease()),
                DiseaseRetrievalDTO.from(diseaseHistorial.getSecondDisease()),
                DiseaseRetrievalDTO.from(diseaseHistorial.getThirdDisease()),
                DiseaseRetrievalDTO.from(diseaseHistorial.getFourthDisease()));
    }

    public static DiseaseHistorial fromDTO(DiseaseHistorialDTO diseaseHistorial) {
        return new DiseaseHistorial(
                new Disease(diseaseHistorial.getFirstDisease().getCode(),
                        diseaseHistorial.getFirstDisease().getName()),
                new Disease(diseaseHistorial.getSecondDisease().getCode(),
                        diseaseHistorial.getSecondDisease().getName()),
                new Disease(diseaseHistorial.getThirdDisease().getCode(),
                        diseaseHistorial.getThirdDisease().getName()),
                new Disease(diseaseHistorial.getFourthDisease().getCode(),
                        diseaseHistorial.getFourthDisease().getName()));
    }
}
