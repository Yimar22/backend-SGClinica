package co.edu.icesi.emt.core.domain.model.clinicalhistory.format;

import java.time.Instant;

public class ClinicalHistoryFormatRetrieval extends ClinicalHistoryFormat {

    private final String id;
    private final Instant createdAt;

    public ClinicalHistoryFormatRetrieval(String id, String name, String description, boolean enabled,
            Instant createdAt, String payload) {
        super(name, description, enabled, payload);
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
