package co.edu.icesi.emt.core.application.dto.clinicalhistory.format;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatLight;

public class ClinicalHistoryFormatLightDTO {

    private final String id;
    private final String name;
    private final String description;
    private final boolean enabled;
    private final Instant createdAt;

    public ClinicalHistoryFormatLightDTO(String id, String name, String description, boolean enabled,
            Instant createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public static ClinicalHistoryFormatLightDTO from(ClinicalHistoryFormatLight clinicalHistoryFormatLight) {
        return new ClinicalHistoryFormatLightDTO(clinicalHistoryFormatLight.getId(),
                clinicalHistoryFormatLight.getName(),
                clinicalHistoryFormatLight.getDescription(), clinicalHistoryFormatLight.isEnabled(),
                clinicalHistoryFormatLight.getCreatedAt());
    }

    public static List<ClinicalHistoryFormatLightDTO> from(
            List<ClinicalHistoryFormatLight> clinicalHistoryFormatLights) {
        return clinicalHistoryFormatLights.stream().map(ClinicalHistoryFormatLightDTO::from)
                .collect(Collectors.toList());
    }
}
