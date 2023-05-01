package co.edu.icesi.emt.core.domain.model.patient.nationality;

public enum MigratoryState {

    REGULAR(1, "Regular"), IRREGULAR(2, "Irregular"), NO_APPLICABLE(3, "No aplica");

    private final int id;
    private final String name;

    private MigratoryState(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static MigratoryState byId(int id) {
        for (MigratoryState migratoryState : MigratoryState.values()) {
            if (migratoryState.getId() == id) {
                return migratoryState;
            }
        }
        return null;
    }
}
