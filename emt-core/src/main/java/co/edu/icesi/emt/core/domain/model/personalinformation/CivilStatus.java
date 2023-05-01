package co.edu.icesi.emt.core.domain.model.personalinformation;

public enum CivilStatus {

    SINGLE(1, "Soltero"),
    MARRIED(2, "Casado"),
    DIVORCED(3, "Divorciado"),
    WIDOWED(4, "Viudo"),
    SEPARATED(5, "Separado"),
    COHABITING(6, "Conviviendo"),
    OTHER(7, "Otro");

    private final int id;
    private final String name;

    private CivilStatus(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CivilStatus findById(int id) {
        for (CivilStatus civilStatus : CivilStatus.values()) {
            if (civilStatus.id == id) {
                return civilStatus;
            }
        }
        return null;
    }
}
