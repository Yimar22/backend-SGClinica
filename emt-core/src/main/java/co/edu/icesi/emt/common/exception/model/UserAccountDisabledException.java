package co.edu.icesi.emt.common.exception.model;

public class UserAccountDisabledException extends Exception implements EMTException {

    private static final String MSG = "This user is not enabled";
    private static final String DOMAIN_MSG = "Esta cuenta de usuario no está habilitada";

    public UserAccountDisabledException() {
        super(MSG);
    }

    @Override
    public String getDomainMessage() {
        return DOMAIN_MSG;
    }
}
