package co.edu.icesi.emt.core.application.dto.feature;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.icesi.emt.core.domain.model.feature.Feature;

public class FeatureDTO {
    private final String name;
    private final String description;
    private final String[] rolesWithAccess;

    @JsonCreator
    public FeatureDTO(@JsonProperty("name") String name, @JsonProperty("description") String description,
            @JsonProperty("rolesWithAccess") String[] rolesWithAccess) {
        this.name = name;
        this.description = description;
        this.rolesWithAccess = rolesWithAccess;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getRolesWithAccess() {
        return rolesWithAccess;
    }

    public static FeatureDTO from(Feature feature) {
        return new FeatureDTO(feature.getName(), feature.getDescription(), feature.getRolesWithAccess());
    }

    public static FeatureDTO[] from(Feature[] feature) {
        FeatureDTO[] featuresDTOs = new FeatureDTO[feature.length];

        for (int i = 0; i < feature.length; i++) {
            featuresDTOs[i] = from(feature[i]);
        }
        return featuresDTOs;
    }
}
