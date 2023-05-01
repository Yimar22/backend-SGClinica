package co.edu.icesi.emt.core.domain.model.feature;

public enum Feature { // For a first realese we will only have the features that are already
                      // implemented and the same way with the roles by default

    USER_LIST("users_list", "Vista que contiene la lista de usuarios", new String[] { "ADMIN", "medical_staff" }),
    PACIENT_LIST("pacients_list", "Vista que contiene la lista de pacientes",
            new String[] { "ADMIN", "medical_staff", "nursing_staff" }),
    CLINICAL_HISTORY_LIST("clinical_history_list", "Vista que contiene la lista de historias clinicas", new String[] {
            "ADMIN", "medical_staff", "nursing_staff" }),
    ROLE_LIST("roles_list", "Vista que contiene la lista de roles", new String[] { "ADMIN" }),
    SUPORT("support", "Opciones de soporte",
            new String[] { "ADMIN", "medical_staff", "nursing_staff", "administrative_staff" });

    private final String name;
    private final String description;
    private final String[] rolesWithAccess;

    Feature(String name, String description, String[] rolesWithAccess) {
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

    public static Feature findByName(String name) {
        for (Feature section : Feature.values()) {
            if (section.getName().equals(name)) {
                return section;
            }
        }
        return null;
    }
}
