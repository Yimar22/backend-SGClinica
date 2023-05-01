package co.edu.icesi.emt.core.domain.model.clinicalhistory.format;

import java.time.Instant;

public class ClinicalHistoryFormatLight {

    private final String id;
    private final String name;
    private final String description;
    private final boolean enabled;
    private final Instant createdAt;

    public ClinicalHistoryFormatLight(String id, String name, String description, boolean enabled, Instant createdAt) {
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
}
