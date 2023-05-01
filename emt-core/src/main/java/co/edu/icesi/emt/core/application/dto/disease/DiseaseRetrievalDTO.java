package co.edu.icesi.emt.core.application.dto.disease;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.domain.model.disease.Disease;

public class DiseaseRetrievalDTO {

    public final String code;
    public final String name;

    @JsonCreator
    public DiseaseRetrievalDTO(@JsonProperty("code") String code, @JsonProperty("name") String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static DiseaseRetrievalDTO from(Disease disease) {
        return new DiseaseRetrievalDTO(disease.getCode(), disease.getName());
    }
}
