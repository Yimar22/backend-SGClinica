package co.edu.icesi.emt.core.application.dto.personalinformation.gender;

import co.edu.icesi.emt.core.domain.model.personalinformation.Gender;

public class GenderDTO {

    private final int id;
    private final String name;

    public GenderDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static GenderDTO from(Gender gender) {
        return new GenderDTO(gender.getId(), gender.getName());
    }

    public static Gender fromDTO(GenderDTO gender) {
        return Gender.findById(gender.getId());
    }
}
