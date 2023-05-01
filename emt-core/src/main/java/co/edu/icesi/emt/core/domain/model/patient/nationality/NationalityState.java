package co.edu.icesi.emt.core.domain.model.patient.nationality;

public class NationalityState {

    private final String nationality;
    private final MigratoryState migratoryState;

    public NationalityState(String nationality, MigratoryState migratoryState) {
        this.nationality = nationality;
        this.migratoryState = migratoryState;
    }

    public String getNationality() {
        return nationality;
    }

    public MigratoryState getMigratoryState() {
        return migratoryState;
    }
}
