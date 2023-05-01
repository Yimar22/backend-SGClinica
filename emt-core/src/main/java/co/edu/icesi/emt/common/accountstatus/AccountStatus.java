package co.edu.icesi.emt.common.accountstatus;

public enum AccountStatus {

    ACTIVE(1, "Activo"), INACTIVE(2, "Inactivo"), BLOCKED(3, "Bloqueado");

    private final int id;
    private final String status;

    private AccountStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public static AccountStatus fromId(int id) {
        for (AccountStatus accountStatus : AccountStatus.values()) {
            if (accountStatus.getId() == id) {
                return accountStatus;
            }
        }
        return null;
    }
}
