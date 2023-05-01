package co.edu.icesi.emt.core.domain.model.personalinformation;

public enum Gender {

    FEMALE(1, "Femenino"),
    MALE(2, "Masculino"),
    NON_BINARY(3, "No binario");

    private final int id;
    private final String name;

    private Gender(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Gender findById(int id) {
        for (Gender gender : Gender.values()) {
            if (gender.id == id) {
                return gender;
            }
        }
        return null;
    }
}
