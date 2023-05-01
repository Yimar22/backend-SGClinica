package co.edu.icesi.emt.core.application.dto.clinicalhistory.format;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormat;

public class ClinicalHistoryFormatCreationDTO {

    private final String name;
    private final String description;
    private final boolean enabled;
    private final String payload;

    @JsonCreator
    public ClinicalHistoryFormatCreationDTO(@JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("enabled") boolean enabled, @JsonProperty("payload") String payload) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.payload = payload;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPayload() {
        return payload;
    }

    public static ClinicalHistoryFormat fromDTO(ClinicalHistoryFormatCreationDTO clinicalHistoryFormatCreationDTO) {
        return new ClinicalHistoryFormat(clinicalHistoryFormatCreationDTO.getName(),
                clinicalHistoryFormatCreationDTO.getDescription(), clinicalHistoryFormatCreationDTO.isEnabled(),
                clinicalHistoryFormatCreationDTO.getPayload());
    }
}
