package co.edu.icesi.emt.core.application.dto.clinicalhistory.format;

import java.time.Instant;

import co.edu.icesi.emt.core.domain.model.clinicalhistory.format.ClinicalHistoryFormatRetrieval;

public class ClinicalHistoryFormatRetrievalDTO {

    private final String id;
    private final String name;
    private final String description;
    private final boolean enabled;
    private final Instant createdAt;
    private final String payload;

    public ClinicalHistoryFormatRetrievalDTO(String id, String name, String description, boolean enabled,
            Instant createdAt, String payload) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.payload = payload;
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

    public String getPayload() {
        return payload;
    }

    public static ClinicalHistoryFormatRetrievalDTO from(
            ClinicalHistoryFormatRetrieval clinicalHistoryFormatRetrieval) {
        return new ClinicalHistoryFormatRetrievalDTO(clinicalHistoryFormatRetrieval.getId(),
                clinicalHistoryFormatRetrieval.getName(), clinicalHistoryFormatRetrieval.getDescription(),
                clinicalHistoryFormatRetrieval.isEnabled(), clinicalHistoryFormatRetrieval.getCreatedAt(),
                clinicalHistoryFormatRetrieval.getPayload());
    }
}
