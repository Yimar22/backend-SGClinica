package co.edu.icesi.emt.core.domain.model.clinicalhistory.format;

public class ClinicalHistoryFormat {

    private final String name;
    private final String description;
    private final boolean enabled;
    private final String payload;

    public ClinicalHistoryFormat(String name, String description, boolean enabled, String payload) {
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
}
